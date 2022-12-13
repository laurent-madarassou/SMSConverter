package tools;

import java.util.HashMap;
import java.util.Iterator;

public class GetFontByCountry {

    private static HashMap<String,CountryCodeFont> hashMapCountry;
    private static CountryCodeFont countryCodeFont ;

    public  GetFontByCountry()

    {
        hashMapCountry =new HashMap<String,CountryCodeFont>();
        countryCodeFont = new CountryCodeFont();
        putCountryInhashMap();
    }

    public  String getFontPath(String iso3Country)
    {

        String ret = "null";

        Iterator<String> keyIterator = hashMapCountry.keySet().iterator();
        while(keyIterator.hasNext()) {
            String key = keyIterator.next();
            String debug = "Code=" + key + "  Country=" + hashMapCountry.get(key);
            if (key.equals(iso3Country)) ret = hashMapCountry.get(key).pathFont;
        }





        return ret;
    }



    class CountryCodeFont
    {


        String continent;
        String country;
        String codeIso3;
        String pathFont;
        boolean noFont;

        private void setValues (   String _continent,String _codeIso3,String _country,String _pathFont,boolean _noFont)
        {
              continent = _continent;
              country = _country;
              codeIso3 = _codeIso3;
              pathFont = _pathFont;
              noFont = _noFont;

        }
    }

    private void putCountryInhashMap()
    {



        
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Angola","AGO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AGO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Ascension Island","ASC","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ASC",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Benin","BEN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BEN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Botswana","BWA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BWA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Burkina Faso","BFA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BFA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Burundi","BDI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BDI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Cameroon","CMR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CMR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Cape Verde","CPV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CPV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Central African Republic","CAF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CAF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Chad","TCD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TCD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Comoros","COM","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("COM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Congo","COG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("COG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Congo, The Democratic Republic Of The","COD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("COD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","CÔte D'ivoire","CIV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CIV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Diego Garcia","DGA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DGA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Djibouti","DJI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DJI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Egypt","EGY","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("EGY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Equatorial Guinea","GNQ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GNQ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Eritrea","ERI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ERI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Ethiopia","ETH","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ETH",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Falkland Islands (malvinas)","FLK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FLK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Gabon","GAB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GAB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Ghana","GHA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GHA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Gibraltar","GIB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GIB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Guinea","GIN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GIN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Guinea-Bissau","GNB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GNB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Kenya","KEN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KEN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Lesotho","LSO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LSO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Liberia","LBR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LBR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Libya","LBY","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("LBY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Madagascar","MDG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MDG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Malawi","MWI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MWI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Mali","MLI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MLI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Mauritania","MRT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MRT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Mauritius","MUS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MUS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Mayotte","MYT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MYT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Morocco","MAR","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("MAR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Mozambique","MOZ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MOZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Namibia","NAM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NAM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Niger","NER","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NER",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Nigeria","NGA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NGA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Réunion","REU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("REU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Rwanda","RWA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("RWA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Saint Helena","SHN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SHN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Sao Tome And Principe","STP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("STP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Senegal","SEN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SEN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Seychelles","SYC","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SYC",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Sierra Leone","SLE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SLE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Somalia","SOM","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("SOM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","South Africa","ZAF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ZAF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Sudan","SDN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SDN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Swaziland","SWZ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SWZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Tanzania, United Republic Of","TZA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TZA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","The Gambia","GMB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GMB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Togo","TGO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TGO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Tristan da Cunha","TAA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TAA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Tunisia","TUN","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("TUN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Uganda","UGA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("UGA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Western Sahara","ESH","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("ESH",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Zambia","ZMB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ZMB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Africa","Zimbabwe","ZWE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ZWE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Antarctica","Antarctica","ATA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ATA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Antarctica","Bouvet Island","BVT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BVT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Antarctica","French Southern Territories","ATF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ATF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Afghanistan","AFG","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("AFG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Armenia","ARM","/system/fonts/NotoSansArmenian-Regular.ttf",true);hashMapCountry.put("ARM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Azerbaijan","AZE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AZE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Bahrain","BHR","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("BHR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Bangladesh","BGD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BGD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Bhutan","BTN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BTN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","British Indian Ocean Territory","IOT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("IOT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Brunei Darussalam","BRN","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("BRN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Burma","MMR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MMR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Cambodia","KHM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KHM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","China","CHN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CHN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Christmas Island","CXR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CXR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Cocos (keeling) Islands","CCK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CCK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Cyprus","CYP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CYP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Guam","GUM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GUM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Hong Kong","HKG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HKG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","India","IND","/system/fonts/NotoSansTamil-Regular.ttf",true);hashMapCountry.put("IND",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Indonesia","IDN","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("IDN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Iran","IRN","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("IRN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Iraq","IRQ","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("IRQ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Israel","ISR","/system/fonts/NotoSansHebrew-Regular.ttf",true);hashMapCountry.put("ISR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Japan","JPN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("JPN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Jordan","JOR","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("JOR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Kazakhstan","KAZ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KAZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Kuwait","KWT","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("KWT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Kyrgyzstan","KGZ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KGZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Lao People's Democratic Republic","LAO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LAO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Lebanon","LBN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LBN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Macao","MAC","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MAC",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Malaysia","MYS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MYS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Maldives","MDV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MDV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Mongolia","MNG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MNG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Nepal","NPL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NPL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","North Korea","PRK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PRK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Oman","OMN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("OMN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Pakistan","PAK","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("PAK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Palestinian Territory, Occupied","PSE","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("PSE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Philippines","PHL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PHL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Qatar","QAT","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("QAT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Russian Federation","RUS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("RUS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Saudi Arabia","SAU","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("SAU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Saudi–Iraqi neutral zone","NTZ","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("NTZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Singapore","SGP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SGP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","South Korea","KOR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KOR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Sri Lanka","LKA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LKA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Syrian Arab Republic","SYR","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("SYR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Taiwan","TWN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TWN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Tajikistan","TJK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TJK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Thailand","THA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("THA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Turkey","TUR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TUR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Turkmenistan","TKM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TKM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","United Arab Emirates","ARE","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("ARE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Uzbekistan","UZB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("UZB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Viet Nam","VNM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VNM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Asia","Yemen","YEM","/system/fonts/NotoNaskhArabicUI-Regular.ttf",true);hashMapCountry.put("YEM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","American Samoa","ASM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ASM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Australia","AUS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AUS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Cook Islands","COK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("COK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","East Timor","TLS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TLS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Fiji","FJI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FJI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","French Polynesia","PYF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PYF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Heard Island And Mcdonald Islands","HMD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HMD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Kiribati","KIR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KIR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Marshall Islands","MHL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MHL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Micronesia, Federated States Of","FSM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FSM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Nauru","NRU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NRU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","New Caledonia","NCL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NCL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","New Zealand","NZL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NZL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Niue","NIU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NIU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Norfolk Island","NFK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NFK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Northern Mariana Islands","MNP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MNP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Palau","PLW","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PLW",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Papua New Guinea","PNG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PNG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Pitcairn","PCN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PCN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Samoa","WSM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("WSM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Solomon Islands","SLB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SLB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Tokelau","TKL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TKL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Tonga","TON","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TON",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Tuvalu","TUV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TUV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Vanuatu","VUT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VUT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Australia","Wallis And Futuna","WLF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("WLF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Åland Islands","ALA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ALA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Albania","ALB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ALB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Andorra","AND","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AND",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Austria","AUT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AUT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Belarus","BLR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BLR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Belgium","BEL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BEL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Bosnia And Herzegovina","BIH","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BIH",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Bulgaria","BGR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BGR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Canary Islands","","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Croatia","HRV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HRV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Czech Republic","CZE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CZE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Denmark","DNK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DNK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Estonia","EST","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("EST",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","European Union","","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Faroe Islands","FRO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FRO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Finland","FIN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FIN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","France","FRA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("FRA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Georgia","GEO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GEO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Germany","DEU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DEU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Greece","GRC","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GRC",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Guernsey","GGY","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GGY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Hungary","HUN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HUN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Iceland","ISL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ISL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Ireland","IRL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("IRL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Isle Of Man","IMN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("IMN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Italy","ITA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ITA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Jersey","JEY","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("JEY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Latvia","LVA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LVA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Liechtenstein","LIE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LIE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Lithuania","LTU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LTU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Luxembourg","LUX","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LUX",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Malta","MLT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MLT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Moldova","MDA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MDA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Monaco","MCO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MCO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Montenegro","MNE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MNE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Netherlands","NLD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NLD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Norway","NOR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NOR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Poland","POL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("POL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Portugal","PRT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PRT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Republic of Macedonia","MKD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MKD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Romania","ROU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ROU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","San Marino","SMR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SMR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Serbia","SRB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SRB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Serbia and Montenegro","SCG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SCG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Slovakia","SVK","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SVK",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Slovenia","SVN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SVN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Soviet Union","SUN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SUN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Spain","ESP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ESP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Svalbard And Jan Mayen","SJM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SJM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Sweden","SWE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SWE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Switzerland","CHE","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CHE",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Ukraine","UKR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("UKR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","United Kingdom","GBR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GBR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("Europe","Vatican City","VAT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VAT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Anguilla","AIA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("AIA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Antigua And Barbuda","ATG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ATG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Aruba","ABW","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ABW",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Bahamas","BHS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BHS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Barbados","BRB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BRB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Belize","BLZ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BLZ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Bermuda","BMU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BMU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","British Virgin Islands","VGB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VGB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Canada","CAN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CAN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Cayman Islands","CYM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CYM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Costa Rica","CRI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CRI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Cuba","CUB","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CUB",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Dominica","DMA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DMA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","El Salvador","SLV","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SLV",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Greenland","GRL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GRL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Grenada","GRD","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GRD",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Guadeloupe","GLP","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GLP",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Guatemala","GTM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GTM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Haiti","HTI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HTI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Honduras","HND","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("HND",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Jamaica","JAM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("JAM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Martinique","MTQ","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MTQ",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Mexico","MEX","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MEX",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Montserrat","MSR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("MSR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Netherlands Antilles","ANT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ANT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Nicaragua","NIC","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("NIC",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Puerto Rico","PRI","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PRI",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Saint Kitts And Nevis","KNA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("KNA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Saint Pierre And Miquelon","SPM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SPM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","Turks And Caicos Islands","TCA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TCA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("North America","United States","USA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("USA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Argentina","ARG","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ARG",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Bolivia","BOL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BOL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Brazil","BRA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("BRA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Chile","CHL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("CHL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Colombia","COL","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("COL",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Dominican Republic","DOM","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("DOM",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Ecuador","ECU","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("ECU",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","French Guiana","GUF","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GUF",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Guyana","GUY","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("GUY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Panama","PAN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PAN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Paraguay","PRY","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PRY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Peru","PER","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("PER",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Saint Lucia","LCA","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("LCA",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Saint Vincent and the Grenadines","VCT","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VCT",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","South Georgia And The South Sandwich Islands","SGS","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SGS",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Suriname","SUR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("SUR",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Trinidad And Tobago","TTO","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("TTO",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Uruguay","URY","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("URY",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Venezuela","VEN","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VEN",countryCodeFont);
        countryCodeFont = new CountryCodeFont();countryCodeFont.setValues("South America","Virgin Islands, U.s.","VIR","/system/fonts/DroidSans.ttf",true);hashMapCountry.put("VIR",countryCodeFont);










    }


}
