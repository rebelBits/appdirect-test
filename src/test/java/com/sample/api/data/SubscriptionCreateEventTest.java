package com.sample.api.data;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXB;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sample.data.appdirect.Address;
import com.sample.data.appdirect.Company;
import com.sample.data.appdirect.Creator;
import com.sample.data.appdirect.Order;
import com.sample.data.appdirect.Payload;
import com.sample.data.appdirect.SubscriptionEvent;

public class SubscriptionCreateEventTest {
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionCreateEventTest.class);

    @Test
    public void unmarshal() {

        try (InputStream xmlInputStream = this.getClass().getClassLoader()
                // .getResourceAsStream("sample-input/dummy-create-event.xml"))
                // {
                .getResourceAsStream("sample-input/create-subscription-event.xml")) {
            final SubscriptionEvent event = JAXB.unmarshal(xmlInputStream, SubscriptionEvent.class);

            // validate the event
            /// validate creator
            final Creator creator = event.getCreator();
            Assert.assertNotNull(creator);
            Assert.assertEquals("User", creator.getLastName());
            Assert.assertEquals("https://www.acme.com/openid/id/47cb8f55-1af6-5bfc-9a7d-8061d3aa0c97",
                    creator.getOpenId());
            Assert.assertEquals("47cb8f55-1af6-5bfc-9a7d-8061d3aa0c97", creator.getUuid());
            Assert.assertEquals("testuser@testco.com", creator.getEmail());

            //// validate creator.address
            final Address address = creator.getAddress();
            Assert.assertNotNull(creator);
            Assert.assertEquals("Test", address.getFirstName());
            Assert.assertEquals("SomeCity", address.getCity());

            /// validate payload
            final Payload payload = event.getPayload();
            Assert.assertNotNull(payload);

            //// validate payload.company
            final Company company = payload.getCompany();
            Assert.assertNotNull(company);
            Assert.assertEquals("tester", company.getName());

            //// validate payload.order
            final Order order = payload.getOrder();
            Assert.assertEquals("Standard", order.getEditionCode());
        } catch (

        final IOException e) {
            LOG.error("Failed to unmarshal the create-subscription event", e);
        }
    }
}
