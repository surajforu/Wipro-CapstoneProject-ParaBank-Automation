package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "billPayData")
    public Object[][] getBillPayData() {

        return new Object[][] {

            {
                "Electricity Board",
                "Park Street",
                "Kolkata",
                "West Bengal",
                "700001",
                "9876543210",
                "12345",
                "100"
            },

            {
                "Water Department",
                "MG Road",
                "Kolkata",
                "West Bengal",
                "700002",
                "9999999999",
                "67890",
                "200"
            }
        };
    }
}