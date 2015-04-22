package com.esweb;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Created by asim on 4/20/15.
 */
public abstract class ElasticSearchConnection {

    protected Client client;

    public ElasticSearchConnection(){

        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "asimes").build();
        client = new TransportClient(settings)
                .addTransportAddress(
                        new InetSocketTransportAddress("localhost", 9300)
                );
    }
}
