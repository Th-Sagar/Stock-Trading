package com.trading.marketdataservice.config;

import com.trading.marketdataservice.entity.Stock;
import com.trading.marketdataservice.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final StockRepository stockRepository;


    @Override
    public void run(String... args) throws Exception {

        if(stockRepository.count()>0){
            log.info("Stocks already initialized -skipping");
            return;
        }

        log.info("Initializing stock data ........");


        List<Stock> stocks = List.of(

                // NEPAL STOCKS

                new Stock("NABIL", "Nabil Bank Limited", BigDecimal.valueOf(550.00), "NEPSE", "NPR", true),
                new Stock("NIMB", "Nepal Investment Mega Bank Limited", BigDecimal.valueOf(200.00), "NEPSE", "NPR", true),
                new Stock("GBIME", "Global IME Bank Limited", BigDecimal.valueOf(255.00), "NEPSE", "NPR", true),
                new Stock("SCB", "Standard Chartered Bank Nepal", BigDecimal.valueOf(680.00), "NEPSE", "NPR", true),
                new Stock("NICA", "NIC Asia Bank Limited", BigDecimal.valueOf(325.00), "NEPSE", "NPR", true),
                new Stock("NMB", "NMB Bank Limited", BigDecimal.valueOf(270.00), "NEPSE", "NPR", true),
                new Stock("EBL", "Everest Bank Limited", BigDecimal.valueOf(715.00), "NEPSE", "NPR", true),
                new Stock("HBL", "Himalayan Bank Limited", BigDecimal.valueOf(205.00), "NEPSE", "NPR", true),
                new Stock("KBL", "Kumari Bank Limited", BigDecimal.valueOf(240.00), "NEPSE", "NPR", true),
                new Stock("SBL", "Siddhartha Bank Limited", BigDecimal.valueOf(430.00), "NEPSE", "NPR", true),
                new Stock("ADBL", "Agricultural Development Bank Limited", BigDecimal.valueOf(335.00), "NEPSE", "NPR", true),
                new Stock("NBL", "Nepal Bank Limited", BigDecimal.valueOf(305.00), "NEPSE", "NPR", true),
                new Stock("PCBL", "Prime Commercial Bank Limited", BigDecimal.valueOf(275.00), "NEPSE", "NPR", true),
                new Stock("SANIMA", "Sanima Bank Limited", BigDecimal.valueOf(395.00), "NEPSE", "NPR", true),
                new Stock("PRVU", "Prabhu Bank Limited", BigDecimal.valueOf(195.00), "NEPSE", "NPR", true),
                new Stock("MBL", "Machhapuchchhre Bank Limited", BigDecimal.valueOf(270.00), "NEPSE", "NPR", true),
                new Stock("SADBL", "Shangri-la Development Bank Limited", BigDecimal.valueOf(420.00), "NEPSE", "NPR", true),
                new Stock("JBBL", "Jyoti Bikas Bank Limited", BigDecimal.valueOf(350.00), "NEPSE", "NPR", true),
                new Stock("LBBL", "Lumbini Bikas Bank Limited", BigDecimal.valueOf(490.00), "NEPSE", "NPR", true),
                new Stock("SHINE", "Shine Resunga Development Bank Limited", BigDecimal.valueOf(420.00), "NEPSE", "NPR", true),

                new Stock("CHCL", "Chilime Hydropower Company Limited", BigDecimal.valueOf(340.00), "NEPSE", "NPR", true),
                new Stock("NHPC", "National Hydro Power Company Limited", BigDecimal.valueOf(260.00), "NEPSE", "NPR", true),
                new Stock("UPPER", "Upper Tamakoshi Hydropower Limited", BigDecimal.valueOf(195.00), "NEPSE", "NPR", true),
                new Stock("API", "Api Power Company Limited", BigDecimal.valueOf(345.00), "NEPSE", "NPR", true),
                new Stock("AHPC", "Arun Valley Hydropower Development Company Limited", BigDecimal.valueOf(260.00), "NEPSE", "NPR", true),
                new Stock("BPCL", "Butwal Power Company Limited", BigDecimal.valueOf(650.00), "NEPSE", "NPR", true),
                new Stock("SHPC", "Sanima Mai Hydropower Limited", BigDecimal.valueOf(530.00), "NEPSE", "NPR", true),
                new Stock("RADHI", "Radhi Bidyut Company Limited", BigDecimal.valueOf(705.00), "NEPSE", "NPR", true),
                new Stock("NGPL", "Ngadi Group Power Limited", BigDecimal.valueOf(400.00), "NEPSE", "NPR", true),
                new Stock("AKPL", "Arun Kabeli Power Limited", BigDecimal.valueOf(255.00), "NEPSE", "NPR", true),
                new Stock("HURJA", "Himal Dolakha Hydropower Company Limited", BigDecimal.valueOf(260.00), "NEPSE", "NPR", true),
                new Stock("RHPL", "Rasuwagadhi Hydropower Company Limited", BigDecimal.valueOf(170.00), "NEPSE", "NPR", true),
                new Stock("SAHAS", "Sahas Urja Limited", BigDecimal.valueOf(700.00), "NEPSE", "NPR", true),
                new Stock("MEN", "Mountain Energy Nepal Limited", BigDecimal.valueOf(665.00), "NEPSE", "NPR", true),
                new Stock("SHIVM", "Shivam Cements Limited", BigDecimal.valueOf(680.00), "NEPSE", "NPR", true),
                new Stock("SONA", "Sonapur Minerals and Oil Limited", BigDecimal.valueOf(435.00), "NEPSE", "NPR", true),
                new Stock("GCIL", "Ghorahi Cement Industry Limited", BigDecimal.valueOf(385.00), "NEPSE", "NPR", true),
                new Stock("HDL", "Himalayan Distillery Limited", BigDecimal.valueOf(1300.00), "NEPSE", "NPR", true),
                new Stock("BNT", "Bottlers Nepal Terai Limited", BigDecimal.valueOf(11900.00), "NEPSE", "NPR", true),
                new Stock("UNL", "Unilever Nepal Limited", BigDecimal.valueOf(40000.00), "NEPSE", "NPR", true),

                new Stock("NIFRA", "Nepal Infrastructure Bank Limited", BigDecimal.valueOf(250.00), "NEPSE", "NPR", true),
                new Stock("HIDCL", "Hydroelectricity Investment and Development Company Limited", BigDecimal.valueOf(255.00), "NEPSE", "NPR", true),
                new Stock("NRIC", "Nepal Reinsurance Company Limited", BigDecimal.valueOf(790.00), "NEPSE", "NPR", true),
                new Stock("RBCL", "Rastriya Beema Company Limited", BigDecimal.valueOf(14200.00), "NEPSE", "NPR", true),
                new Stock("NICL", "Nepal Insurance Company Limited", BigDecimal.valueOf(450.00), "NEPSE", "NPR", true),
                new Stock("NLIC", "Nepal Life Insurance Company Limited", BigDecimal.valueOf(745.00), "NEPSE", "NPR", true),
                new Stock("LICN", "Life Insurance Corporation Nepal Limited", BigDecimal.valueOf(800.00), "NEPSE", "NPR", true),
                new Stock("IGI", "IGI Prudential Insurance Limited", BigDecimal.valueOf(405.00), "NEPSE", "NPR", true),
                new Stock("SICL", "Shikhar Insurance Company Limited", BigDecimal.valueOf(550.00), "NEPSE", "NPR", true),
                new Stock("NLG", "NLG Insurance Company Limited", BigDecimal.valueOf(510.00), "NEPSE", "NPR", true),

                new Stock("CBBL", "Chhimek Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(975.00), "NEPSE", "NPR", true),
                new Stock("DDBL", "Deprosc Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(865.00), "NEPSE", "NPR", true),
                new Stock("SKBBL", "Sana Kisan Bikas Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(760.00), "NEPSE", "NPR", true),
                new Stock("NUBL", "Nirdhan Utthan Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(600.00), "NEPSE", "NPR", true),
                new Stock("FMDBL", "First Microfinance Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(725.00), "NEPSE", "NPR", true),
                new Stock("JBLB", "Jeevan Bikas Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(1280.00), "NEPSE", "NPR", true),
                new Stock("NMBMF", "NMB Microfinance Bittiya Sanstha Limited", BigDecimal.valueOf(600.00), "NEPSE", "NPR", true),
                new Stock("SWBBL", "Swabalamban Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(705.00), "NEPSE", "NPR", true),
                new Stock("LLBS", "Laxmi Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(810.00), "NEPSE", "NPR", true),
                new Stock("MLBBL", "Mithila Laghubitta Bittiya Sanstha Limited", BigDecimal.valueOf(930.00), "NEPSE", "NPR", true),

                new Stock("NTC", "Nepal Doorsanchar Company Limited", BigDecimal.valueOf(910.00), "NEPSE", "NPR", true),
                new Stock("STC", "Salt Trading Corporation Limited", BigDecimal.valueOf(5560.00), "NEPSE", "NPR", true),
                new Stock("BBC", "Bishal Bazar Company Limited", BigDecimal.valueOf(4000.00), "NEPSE", "NPR", true),
                new Stock("SHL", "Soaltee Hotel Limited", BigDecimal.valueOf(520.00), "NEPSE", "NPR", true),
                new Stock("OHL", "Oriental Hotels Limited", BigDecimal.valueOf(640.00), "NEPSE", "NPR", true),
                new Stock("TRH", "Taragaon Regency Hotels Limited", BigDecimal.valueOf(825.00), "NEPSE", "NPR", true),
                new Stock("CGH", "Chandragiri Hills Limited", BigDecimal.valueOf(750.00), "NEPSE", "NPR", true),
                new Stock("CITY", "City Hotel Limited", BigDecimal.valueOf(325.00), "NEPSE", "NPR", true),
                new Stock("HRL", "Himalayan Reinsurance Limited", BigDecimal.valueOf(475.00), "NEPSE", "NPR", true),
                new Stock("NRN", "NRN Infrastructure and Development Limited", BigDecimal.valueOf(1380.00), "NEPSE", "NPR", true),

                new Stock("BARUN", "Barun Hydropower Company Limited", BigDecimal.valueOf(330.00), "NEPSE", "NPR", true),
                new Stock("BEDC", "Bhugol Energy Development Company Limited", BigDecimal.valueOf(295.00), "NEPSE", "NPR", true),
                new Stock("BHCL", "Bikash Hydropower Company Limited", BigDecimal.valueOf(525.00), "NEPSE", "NPR", true),
                new Stock("BHL", "Balephi Hydropower Limited", BigDecimal.valueOf(225.00), "NEPSE", "NPR", true),
                new Stock("DORDI", "Dordi Khola Jal Bidyut Company Limited", BigDecimal.valueOf(265.00), "NEPSE", "NPR", true),
                new Stock("EHPL", "Eastern Hydropower Limited", BigDecimal.valueOf(320.00), "NEPSE", "NPR", true),
                new Stock("GHL", "Ghalemdi Hydro Limited", BigDecimal.valueOf(305.00), "NEPSE", "NPR", true),
                new Stock("JOSHI", "Joshi Hydropower Development Company Limited", BigDecimal.valueOf(260.00), "NEPSE", "NPR", true),
                new Stock("KKHC", "Khanikhola Hydropower Company Limited", BigDecimal.valueOf(275.00), "NEPSE", "NPR", true),
                new Stock("LEC", "Liberty Energy Company Limited", BigDecimal.valueOf(245.00), "NEPSE", "NPR", true),

                new Stock("MHL", "Mandakini Hydropower Limited", BigDecimal.valueOf(450.00), "NEPSE", "NPR", true),
                new Stock("MKHC", "Maya Khola Hydropower Company Limited", BigDecimal.valueOf(255.00), "NEPSE", "NPR", true),
                new Stock("MKHL", "Mai Khola Hydropower Limited", BigDecimal.valueOf(495.00), "NEPSE", "NPR", true),
                new Stock("NYADI", "Nyadi Hydropower Limited", BigDecimal.valueOf(340.00), "NEPSE", "NPR", true),
                new Stock("PPCL", "Panchthar Power Company Limited", BigDecimal.valueOf(330.00), "NEPSE", "NPR", true),
                new Stock("PPL", "People's Power Limited", BigDecimal.valueOf(320.00), "NEPSE", "NPR", true),
                new Stock("RIDI", "Ridi Power Company Limited", BigDecimal.valueOf(345.00), "NEPSE", "NPR", true),
                new Stock("SMHL", "Supermai Hydropower Limited", BigDecimal.valueOf(480.00), "NEPSE", "NPR", true),
                new Stock("UPCL", "Universal Power Company Limited", BigDecimal.valueOf(330.00), "NEPSE", "NPR", true),
                new Stock("USHEC", "Upper Solu Hydro Electric Company Limited", BigDecimal.valueOf(505.00), "NEPSE", "NPR", true),

                // US STOCKS

                new Stock("AAPL", "Apple Inc.", BigDecimal.valueOf(189.50), "NASDAQ", "USD", true),
                new Stock("MSFT", "Microsoft Corporation", BigDecimal.valueOf(425.20), "NASDAQ", "USD", true),
                new Stock("GOOGL", "Alphabet Inc.", BigDecimal.valueOf(175.30), "NASDAQ", "USD", true),
                new Stock("AMZN", "Amazon.com Inc.", BigDecimal.valueOf(185.75), "NASDAQ", "USD", true),
                new Stock("NVDA", "NVIDIA Corporation", BigDecimal.valueOf(950.40), "NASDAQ", "USD", true),
                new Stock("META", "Meta Platforms Inc.", BigDecimal.valueOf(505.60), "NASDAQ", "USD", true),
                new Stock("TSLA", "Tesla Inc.", BigDecimal.valueOf(175.20), "NASDAQ", "USD", true),
                new Stock("NFLX", "Netflix Inc.", BigDecimal.valueOf(625.80), "NASDAQ", "USD", true),
                new Stock("AMD", "Advanced Micro Devices Inc.", BigDecimal.valueOf(165.40), "NASDAQ", "USD", true),
                new Stock("INTC", "Intel Corporation", BigDecimal.valueOf(35.60), "NASDAQ", "USD", true),

                new Stock("ORCL", "Oracle Corporation", BigDecimal.valueOf(125.30), "NYSE", "USD", true),
                new Stock("CRM", "Salesforce Inc.", BigDecimal.valueOf(272.50), "NYSE", "USD", true),
                new Stock("ADBE", "Adobe Inc.", BigDecimal.valueOf(485.70), "NASDAQ", "USD", true),
                new Stock("CSCO", "Cisco Systems Inc.", BigDecimal.valueOf(48.90), "NASDAQ", "USD", true),
                new Stock("QCOM", "Qualcomm Inc.", BigDecimal.valueOf(175.60), "NASDAQ", "USD", true),
                new Stock("IBM", "International Business Machines", BigDecimal.valueOf(190.20), "NYSE", "USD", true),
                new Stock("AVGO", "Broadcom Inc.", BigDecimal.valueOf(1350.50), "NASDAQ", "USD", true),
                new Stock("TXN", "Texas Instruments Inc.", BigDecimal.valueOf(195.80), "NASDAQ", "USD", true),
                new Stock("NOW", "ServiceNow Inc.", BigDecimal.valueOf(720.40), "NYSE", "USD", true),
                new Stock("INTU", "Intuit Inc.", BigDecimal.valueOf(625.30), "NASDAQ", "USD", true),

                new Stock("JPM", "JPMorgan Chase & Co.", BigDecimal.valueOf(198.50), "NYSE", "USD", true),
                new Stock("BAC", "Bank of America Corporation", BigDecimal.valueOf(38.20), "NYSE", "USD", true),
                new Stock("WFC", "Wells Fargo & Company", BigDecimal.valueOf(58.40), "NYSE", "USD", true),
                new Stock("GS", "Goldman Sachs Group Inc.", BigDecimal.valueOf(455.60), "NYSE", "USD", true),
                new Stock("MS", "Morgan Stanley", BigDecimal.valueOf(98.70), "NYSE", "USD", true),
                new Stock("C", "Citigroup Inc.", BigDecimal.valueOf(62.30), "NYSE", "USD", true),
                new Stock("BLK", "BlackRock Inc.", BigDecimal.valueOf(785.50), "NYSE", "USD", true),
                new Stock("AXP", "American Express Company", BigDecimal.valueOf(230.80), "NYSE", "USD", true),
                new Stock("V", "Visa Inc.", BigDecimal.valueOf(275.40), "NYSE", "USD", true),
                new Stock("MA", "Mastercard Incorporated", BigDecimal.valueOf(465.20), "NYSE", "USD", true),

                new Stock("JNJ", "Johnson & Johnson", BigDecimal.valueOf(155.60), "NYSE", "USD", true),
                new Stock("PFE", "Pfizer Inc.", BigDecimal.valueOf(28.50), "NYSE", "USD", true),
                new Stock("MRK", "Merck & Co. Inc.", BigDecimal.valueOf(128.70), "NYSE", "USD", true),
                new Stock("ABBV", "AbbVie Inc.", BigDecimal.valueOf(165.40), "NYSE", "USD", true),
                new Stock("LLY", "Eli Lilly and Company", BigDecimal.valueOf(780.50), "NYSE", "USD", true),
                new Stock("UNH", "UnitedHealth Group Inc.", BigDecimal.valueOf(485.20), "NYSE", "USD", true),
                new Stock("TMO", "Thermo Fisher Scientific Inc.", BigDecimal.valueOf(560.30), "NYSE", "USD", true),
                new Stock("ABT", "Abbott Laboratories", BigDecimal.valueOf(105.60), "NYSE", "USD", true),
                new Stock("DHR", "Danaher Corporation", BigDecimal.valueOf(250.80), "NYSE", "USD", true),
                new Stock("AMGN", "Amgen Inc.", BigDecimal.valueOf(305.40), "NASDAQ", "USD", true),

                new Stock("KO", "Coca-Cola Company", BigDecimal.valueOf(62.50), "NYSE", "USD", true),
                new Stock("PEP", "PepsiCo Inc.", BigDecimal.valueOf(175.30), "NASDAQ", "USD", true),
                new Stock("MCD", "McDonald's Corporation", BigDecimal.valueOf(275.60), "NYSE", "USD", true),
                new Stock("SBUX", "Starbucks Corporation", BigDecimal.valueOf(78.40), "NASDAQ", "USD", true),
                new Stock("NKE", "Nike Inc.", BigDecimal.valueOf(95.80), "NYSE", "USD", true),
                new Stock("WMT", "Walmart Inc.", BigDecimal.valueOf(68.50), "NYSE", "USD", true),
                new Stock("COST", "Costco Wholesale Corporation", BigDecimal.valueOf(825.40), "NASDAQ", "USD", true),
                new Stock("TGT", "Target Corporation", BigDecimal.valueOf(145.60), "NYSE", "USD", true),
                new Stock("HD", "Home Depot Inc.", BigDecimal.valueOf(355.20), "NYSE", "USD", true),
                new Stock("LOW", "Lowe's Companies Inc.", BigDecimal.valueOf(235.80), "NYSE", "USD", true),

                new Stock("DIS", "Walt Disney Company", BigDecimal.valueOf(112.50), "NYSE", "USD", true),
                new Stock("CMCSA", "Comcast Corporation", BigDecimal.valueOf(39.80), "NASDAQ", "USD", true),
                new Stock("T", "AT&T Inc.", BigDecimal.valueOf(17.20), "NYSE", "USD", true),
                new Stock("VZ", "Verizon Communications Inc.", BigDecimal.valueOf(40.60), "NYSE", "USD", true),
                new Stock("TMUS", "T-Mobile US Inc.", BigDecimal.valueOf(165.30), "NASDAQ", "USD", true),
                new Stock("CHTR", "Charter Communications Inc.", BigDecimal.valueOf(285.40), "NASDAQ", "USD", true),
                new Stock("SPOT", "Spotify Technology S.A.", BigDecimal.valueOf(310.50), "NYSE", "USD", true),
                new Stock("UBER", "Uber Technologies Inc.", BigDecimal.valueOf(75.80), "NYSE", "USD", true),
                new Stock("ABNB", "Airbnb Inc.", BigDecimal.valueOf(155.60), "NASDAQ", "USD", true),
                new Stock("BKNG", "Booking Holdings Inc.", BigDecimal.valueOf(3650.50), "NASDAQ", "USD", true),

                new Stock("XOM", "Exxon Mobil Corporation", BigDecimal.valueOf(115.80), "NYSE", "USD", true),
                new Stock("CVX", "Chevron Corporation", BigDecimal.valueOf(158.40), "NYSE", "USD", true),
                new Stock("COP", "ConocoPhillips", BigDecimal.valueOf(125.60), "NYSE", "USD", true),
                new Stock("SLB", "Schlumberger Limited", BigDecimal.valueOf(48.90), "NYSE", "USD", true),
                new Stock("OXY", "Occidental Petroleum Corporation", BigDecimal.valueOf(62.50), "NYSE", "USD", true),
                new Stock("NEE", "NextEra Energy Inc.", BigDecimal.valueOf(72.40), "NYSE", "USD", true),
                new Stock("DUK", "Duke Energy Corporation", BigDecimal.valueOf(102.80), "NYSE", "USD", true),
                new Stock("SO", "Southern Company", BigDecimal.valueOf(78.30), "NYSE", "USD", true),
                new Stock("ENPH", "Enphase Energy Inc.", BigDecimal.valueOf(115.60), "NASDAQ", "USD", true),
                new Stock("FSLR", "First Solar Inc.", BigDecimal.valueOf(185.40), "NASDAQ", "USD", true),

                new Stock("F", "Ford Motor Company", BigDecimal.valueOf(12.50), "NYSE", "USD", true),
                new Stock("GM", "General Motors Company", BigDecimal.valueOf(45.80), "NYSE", "USD", true),
                new Stock("TM", "Toyota Motor Corporation", BigDecimal.valueOf(215.60), "NYSE", "USD", true),
                new Stock("RIVN", "Rivian Automotive Inc.", BigDecimal.valueOf(11.80), "NASDAQ", "USD", true),
                new Stock("LCID", "Lucid Group Inc.", BigDecimal.valueOf(2.90), "NASDAQ", "USD", true),
                new Stock("NIO", "NIO Inc.", BigDecimal.valueOf(5.60), "NYSE", "USD", true),
                new Stock("BABA", "Alibaba Group Holding Limited", BigDecimal.valueOf(78.50), "NYSE", "USD", true),
                new Stock("JD", "JD.com Inc.", BigDecimal.valueOf(32.40), "NASDAQ", "USD", true),
                new Stock("PDD", "PDD Holdings Inc.", BigDecimal.valueOf(145.60), "NASDAQ", "USD", true),
                new Stock("TSM", "Taiwan Semiconductor Manufacturing", BigDecimal.valueOf(175.80), "NYSE", "USD", true),

                new Stock("CAT", "Caterpillar Inc.", BigDecimal.valueOf(345.60), "NYSE", "USD", true),
                new Stock("DE", "Deere & Company", BigDecimal.valueOf(385.40), "NYSE", "USD", true),
                new Stock("GE", "GE Aerospace", BigDecimal.valueOf(165.20), "NYSE", "USD", true),
                new Stock("HON", "Honeywell International Inc.", BigDecimal.valueOf(205.80), "NASDAQ", "USD", true),
                new Stock("BA", "Boeing Company", BigDecimal.valueOf(175.60), "NYSE", "USD", true),
                new Stock("LMT", "Lockheed Martin Corporation", BigDecimal.valueOf(465.30), "NYSE", "USD", true),
                new Stock("RTX", "RTX Corporation", BigDecimal.valueOf(105.80), "NYSE", "USD", true),
                new Stock("UPS", "United Parcel Service Inc.", BigDecimal.valueOf(145.60), "NYSE", "USD", true),
                new Stock("FDX", "FedEx Corporation", BigDecimal.valueOf(265.40), "NYSE", "USD", true),
                new Stock("MMM", "3M Company", BigDecimal.valueOf(102.50), "NYSE", "USD", true),

                new Stock("PYPL", "PayPal Holdings Inc.", BigDecimal.valueOf(65.40), "NASDAQ", "USD", true),
                new Stock("SQ", "Block Inc.", BigDecimal.valueOf(72.80), "NYSE", "USD", true),
                new Stock("COIN", "Coinbase Global Inc.", BigDecimal.valueOf(225.60), "NASDAQ", "USD", true),
                new Stock("PLTR", "Palantir Technologies Inc.", BigDecimal.valueOf(24.50), "NASDAQ", "USD", true),
                new Stock("SNOW", "Snowflake Inc.", BigDecimal.valueOf(165.80), "NYSE", "USD", true),
                new Stock("PANW", "Palo Alto Networks Inc.", BigDecimal.valueOf(285.40), "NASDAQ", "USD", true),
                new Stock("CRWD", "CrowdStrike Holdings Inc.", BigDecimal.valueOf(315.60), "NASDAQ", "USD", true),
                new Stock("NET", "Cloudflare Inc.", BigDecimal.valueOf(78.90), "NYSE", "USD", true),
                new Stock("DDOG", "Datadog Inc.", BigDecimal.valueOf(125.40), "NASDAQ", "USD", true),
                new Stock("ZS", "Zscaler Inc.", BigDecimal.valueOf(185.60), "NASDAQ", "USD", true)
        );

        stockRepository.saveAll(stocks);

        log.info("Initialized {} stocks successfully.",stocks);

    }
}
