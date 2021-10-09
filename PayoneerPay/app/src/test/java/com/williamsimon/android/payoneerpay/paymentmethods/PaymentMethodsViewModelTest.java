package com.williamsimon.android.payoneerpay.paymentmethods;

import com.williamsimon.android.payoneerpay.domain.ApplicableNetwork;
import com.williamsimon.android.payoneerpay.domain.ListResult;
import com.williamsimon.android.payoneerpay.domain.Networks;
import com.williamsimon.android.payoneerpay.network.FakePaymentMethodsService;
import com.williamsimon.android.payoneerpay.network.PaymentMethodsService;
import com.williamsimon.android.payoneerpay.utils.ApiStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static com.williamsimon.android.payoneerpay.LiveDataTestUtil.getOrAwaitValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentMethodsViewModelTest {

    private PaymentMethodsViewModel viewModel;
    private FakePaymentMethodsService service;


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        service = new FakePaymentMethodsService();
        viewModel = new PaymentMethodsViewModel(service);
    }

    @Test
    public void getApplicableNetworksResponse_setsApplicableNetworksValueAndStatusDone() throws MalformedURLException, InterruptedException {

        //Given available list result
        service.setListResultData(createDummyData());

        //When initializing the view model
        viewModel.init();

        //Then list result value is set and status value is done
        ListResult listResultValue;
        listResultValue = getOrAwaitValue(viewModel.getListResult());
        assertThat(listResultValue, not(nullValue()));
        ApiStatus statusValue = getOrAwaitValue(viewModel.getStatus());
        assertThat(statusValue, is(ApiStatus.DONE));


    }

    ListResult createDummyData() throws MalformedURLException {
        ListResult dummyListResult = new ListResult();
        Map<String, URL> links = new HashMap<>();
        links.put("self", new URL("https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it"));
        links.put("lang", new URL("https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/checkout.json"));
        dummyListResult.setLinks(links);
        ApplicableNetwork network1 = new ApplicableNetwork();
        Map<String, URL> networkLinks1 = new HashMap<>();
        networkLinks1.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png"));
        network1.setLinks(networkLinks1);
        network1.setCode("AMEX");
        network1.setLabel("American Express");
        ApplicableNetwork network2 = new ApplicableNetwork();
        Map<String, URL> networkLinks2 = new HashMap<>();
        networkLinks2.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/diners.png"));
        network2.setLinks(networkLinks2);
        network2.setCode("DINERS");
        network2.setLabel("Diners Club");
        ApplicableNetwork network3 = new ApplicableNetwork();
        Map<String, URL> networkLinks3 = new HashMap<>();
        networkLinks3.put("logo", new URL("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/discover.png"));
        network3.setLinks(networkLinks3);
        network1.setCode("DISCOVER");
        network1.setLabel("Discover");
        List<ApplicableNetwork> applicableNetworks = new ArrayList<>();
        applicableNetworks.add(network1);
        applicableNetworks.add(network2);
        applicableNetworks.add(network3);
        Networks networks = new Networks();
        networks.setApplicable(applicableNetworks);
        dummyListResult.setNetworks(networks);
        return dummyListResult;
    }

}
