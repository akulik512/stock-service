package com.akulik.stockservice.integration;

import com.akulik.stockservice.stock.domain.model.Stock;
import com.akulik.stockservice.stock.persistance.entity.StockEntity;
import com.akulik.stockservice.stock.persistance.repository.StockRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static com.akulik.stockservice.testutil.StockTestData.buildStock;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockIntegrationTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldSaveStock() throws Exception {
        final String requestBody = new JSONObject()
                .put("companyName", "Stock Service")
                .put("shareName", "NSE:DRREDDY")
                .put("shareIsinCode", "US-000402625-0")
                .put("country", "Sweden")
                .put("fieldEconomicActivity", "crypto")
                .put("pricePerShare", "12.34 EUR")
                .put("volume", "2")
                .put("date", "20221219")
                .put("employeeId", "12345")
                .toString();

        mockMvc.perform(post("/stock")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
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
        final String invalidRequestBody = new JSONObject()
                .put("companyName", "Stock Service")
                .put("shareName", "NSE:DRREDDY")
                .put("shareIsinCode", "US-000402625-0")
                .put("country", "Sweden")
                .put("fieldEconomicActivity", "crypto")
                .put("pricePerShare", "12.34 USD")
                .put("volume", "2")
                .put("date", "20221219")
                .put("employeeId", "12345")
                .toString();

        assertThatThrownBy(() -> mockMvc.perform(post("/stock")
                        .content(invalidRequestBody)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()))
                .isInstanceOf(NestedServletException.class);
        assertThat(stockRepository.findAll().iterator().hasNext()).isFalse();
    }

}
