package com.akulik.stockservice.integration;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.repository.StockRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.NestedServletException;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
    }

    @Test
    void shouldSaveStock() throws Exception {
        final String requestBody = buildRequestBody("12.34 EUR", "12345");

        mockMvc.perform(performStockSaving(requestBody))
                .andExpect(status().isOk());

        final StockEntity savedStock = stockRepository.findAll().iterator().next();
        final Stock expectedResult = buildStock();
        assertThat(savedStock)
                .usingRecursiveComparison()
                .ignoringFields("stockId")
                .isEqualTo(expectedResult);
    }

    @Test
    void shouldNotSaveStock() throws Exception {
        final String invalidRequestBody = buildRequestBody("12.34 USD", "12345");

        assertThatThrownBy(() -> mockMvc.perform(performStockSaving(invalidRequestBody))
                .andExpect(status().isOk()))
                .isInstanceOf(NestedServletException.class);
        assertThat(stockRepository.findAll().iterator().hasNext()).isFalse();
    }

    @Test
    void shouldFindAllStocksByEmployeeId() throws Exception {
        final String employeeId = "12345";
        final String stock = buildRequestBody("12.34 EUR", employeeId);

        mockMvc.perform(performStockSaving(stock))
                .andExpect(status().isOk());
        mockMvc.perform(performStockSaving(stock))
                .andExpect(status().isOk());

        final String resultActions = mockMvc.perform(get(String.format("/stock/%s", employeeId))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        final String expectedResult = buildGetAllStocksExpectedResponse(resultActions);
        JSONAssert.assertEquals(expectedResult, resultActions, JSONCompareMode.NON_EXTENSIBLE);
//        JSONAssert.assertEquals(expectedResult, resultActions,
//                new CustomComparator(JSONCompareMode.LENIENT,
//                        new Customization("stockId", (o1, o2) -> true)));
    }

    private String buildRequestBody(String value, String employeeId) throws JSONException {
        return new JSONObject()
                .put("companyName", "Stock Service")
                .put("shareName", "NSE:DRREDDY")
                .put("shareIsinCode", "US-000402625-0")
                .put("country", "Sweden")
                .put("fieldEconomicActivity", "crypto")
                .put("pricePerShare", value)
                .put("volume", "2")
                .put("date", "20221219")
                .put("employeeId", employeeId)
                .toString();
    }

    private MockHttpServletRequestBuilder performStockSaving(String requestBody) {
        return post("/stock")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private String buildGetAllStocksExpectedResponse(String resultActions) {
        final int stock1BeginIndex = 13;
        final int stock1EndIndex = 49;
        final int stock2BeginIndex = 281;
        final int stock2EndIndex = 317;
        final String actualStockId1 = resultActions.substring(stock1BeginIndex, stock1EndIndex);
        final String actualStockId2 = resultActions.substring(stock2BeginIndex, stock2EndIndex);
        return "[\n" +
                "   {\n" +
                "      \"stockId\":\"" + "" + actualStockId1 + "\",\n" +
                "      \"companyName\":\"Stock Service\",\n" +
                "      \"shareName\":\"NSE:DRREDDY\",\n" +
                "      \"shareIsinCode\":\"US-000402625-0\",\n" +
                "      \"country\":\"Sweden\",\n" +
                "      \"fieldEconomicActivity\":\"crypto\",\n" +
                "      \"pricePerShare\":\"12.34 EUR\",\n" +
                "      \"volume\":2,\n" +
                "      \"date\":\"20221219\",\n" +
                "      \"employeeId\":12345\n" +
                "   },\n" +
                "   {\n" +
                "      \"stockId\":\"" + "" + actualStockId2 + "\",\n" +
                "      \"companyName\":\"Stock Service\",\n" +
                "      \"shareName\":\"NSE:DRREDDY\",\n" +
                "      \"shareIsinCode\":\"US-000402625-0\",\n" +
                "      \"country\":\"Sweden\",\n" +
                "      \"fieldEconomicActivity\":\"crypto\",\n" +
                "      \"pricePerShare\":\"12.34 EUR\",\n" +
                "      \"volume\":2,\n" +
                "      \"date\":\"20221219\",\n" +
                "      \"employeeId\":12345\n" +
                "   }\n" +
                "]";
    }

}
