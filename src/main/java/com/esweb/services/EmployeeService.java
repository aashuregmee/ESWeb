package com.esweb.services;

import com.esweb.ElasticSearchConnection;
import com.esweb.commons.ESWebConstants;
import com.esweb.domain.Employee;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;

import java.util.*;

/**
 * Created by asim on 4/20/15.
 */
public class EmployeeService extends ElasticSearchConnection {

    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

    public boolean insertEmployee(Employee newEmployee) throws NoNodeAvailableException{

        Map<String, Object> employeeMap = new HashMap<String, Object>();
        employeeMap.put(ESWebConstants.ID, newEmployee.getEmpId());
        employeeMap.put(ESWebConstants.FIRST_NAME, newEmployee.getFirstName());
        employeeMap.put(ESWebConstants.LAST_NAME, newEmployee.getLastName());
        employeeMap.put(ESWebConstants.AGE, newEmployee.getAge());
        employeeMap.put(ESWebConstants.GENDER, newEmployee.getGender());
        employeeMap.put(ESWebConstants.SALARY, newEmployee.getSalary());
        employeeMap.put(ESWebConstants.DEPARTMENT, newEmployee.getDepartment());

        IndexResponse indexResponse = client.prepareIndex(ESWebConstants.INDEX_NAME, ESWebConstants.TYPE_NAME,
                                                        String.valueOf(newEmployee.getEmpId()))
                                            .setSource(employeeMap)
                                            .execute()
                                            .actionGet();
        return indexResponse.isCreated();
    }

    public List<Employee> search(int empId, String firstName, String lastName, String gender,
                                 int age, double salary, String department) throws NoNodeAvailableException{

        List<Employee> employees;

        if(firstName != null && !firstName.isEmpty())
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.FIRST_NAME, firstName));
        if(lastName != null && !lastName.isEmpty())
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.LAST_NAME, lastName));
        if(gender != null && !gender.isEmpty())
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.GENDER, gender));
        if(department != null && !department.isEmpty())
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.DEPARTMENT, department));
        if(salary != 0)
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.SALARY, salary));
        if(empId != 0)
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.ID, empId));
        if(age != 0)
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery(ESWebConstants.AGE, age));

        employees = searchEmployees();

        return employees;
    }

    private List<Employee> searchEmployees() throws NoNodeAvailableException{

        SearchResponse response = client.prepareSearch(ESWebConstants.INDEX_NAME)
                .setSize(100)
                .setTypes(ESWebConstants.TYPE_NAME)
                .setQuery(boolQueryBuilder)
                .execute()
                .actionGet();

        return getEmployeesFromSearchHits(response.getHits());
    }

    private List<Employee> getEmployeesFromSearchHits(SearchHits searchHits){

        Iterator<SearchHit> searchHitIterator = searchHits.iterator();
        List<Employee> employees = new ArrayList<Employee>();

        while(searchHitIterator.hasNext()){

            SearchHit searchHit = searchHitIterator.next();
            Employee employee = new Employee();

            employee.setEmpId((Integer) searchHit.getSource().get(ESWebConstants.ID));
            employee.setFirstName((String) searchHit.getSource().get(ESWebConstants.FIRST_NAME));
            employee.setLastName((String) searchHit.getSource().get(ESWebConstants.LAST_NAME));
            employee.setGender((String) searchHit.getSource().get(ESWebConstants.GENDER));
            employee.setAge((Integer) searchHit.getSource().get(ESWebConstants.AGE));
            employee.setSalary((Double) searchHit.getSource().get(ESWebConstants.SALARY));
            employee.setDepartment((String) searchHit.getSource().get(ESWebConstants.DEPARTMENT));

            employees.add(employee);
        }

        return employees;
    }

    public int getMaxId(){

        SearchResponse searchResponse = client.prepareSearch(ESWebConstants.INDEX_NAME)
                .setTypes(ESWebConstants.TYPE_NAME)
                .addAggregation(AggregationBuilders.max("maxId").field("id"))
                .execute()
                .actionGet();
        Max maxId = searchResponse.getAggregations().get("maxId");

        return (int) maxId.getValue();
    }
}