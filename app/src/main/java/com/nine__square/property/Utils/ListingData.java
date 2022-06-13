package com.nine__square.property.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nine__square.property.Models.LatestDataModel;

public class ListingData {
    private static ListingData mInstance;
    private static Context mContext;

    private static final String SHARED_NAME = "99Acres";


    //basic details;
    private static final String USERTYPE = "usertype";
    private static final String TYPE = "type";
    private static final String CATEGORY = "category";
    private static final String CATEGORYTYPE = "categorytype";
    private static final String AGREMENT = "agrement";
    private static final String PGAVAILABLEFOR = "pgavaliablefor";
    private static final String PGSHAREPRIVATE = "pgshareprivate";
    private static final String SHARINGSPINNUMBER = "sharingspinnumber";
    private static final String PROPERTYLISTFOR = "propertylistfor";
    private static final String WILLING = "willing";
    private static final String PGSUITABLEFOR = "pgsuitablefor";



    //location
    private static final String STATE = "state";
    private static final String CITY = "city";
    private static final String LOCALITY = "locality";
    private static final String PROJECTNAME = "projectname";
    private static final String ADDRESS = "address";
    private static final String LATLOG = "latlog";



    // PROPERTY DETAILS


    private static final String UNIT1 = "unit1";
    private static final String UNIT2 = "unit2";
    private static final String UNIT3 = "unit3";
    private static final String AVAILABILITYDATE = "availabilitydate";
    private static final String OTHERROOM = "otherroom";
    private static final String HOMETHINGS = "homethings";
    private static final String BUILTUPA = "builtupa";
    private static final String CARPETA = "carpeta";
    private static final String PLOTA = "plota";
    private static final String BED = "bed";
    private static final String BATH = "bath";
    private static final String BALCONIE = "balconie";
    private static final String TOTALFLOORGET = "totalfloorget";
    private static final String RESERVEDPARK = "reservedpark";
    private static final String AVAILABILITYSPINGET = "availabilityspinget";
    private static final String AGEOFPROPERTYSPINGET = "ageofpropertyspinget";
    private static final String FURNISHEDSPINGET = "furnishedspinget";
    private static final String POSSESSIONBYSPINGET = "possessionbyspinget";
    private static final String NOOFROOM = "noofratingspinget";
    private static final String QUALITYRATING = "qualityratingspinget";
    private static final String FLOORALLOWED = "floorallowedspinget";
    private static final String ROOMAVAILABLESPINGET = "roomavailabespinget";
    private static final String FLATTYPESPINGET = "flattypespinget";
    private static final String RERA = "rera";



    //Add property detail changes

    private static final String SPIFLATTYPENEW = "spiFlatTypeNew";
    private static final String EDTSTUDIOAPRTNO = "edtStudioAprtNo";
    private static final String EDTPENTHOUSENO = "edtPentHouseNo";
    private static final String EDTROWHOUSENO = "edtRowHouseNo";
    private static final String EDTDUPLEXNO = "edtDuplexNo";
    private static final String EDTSHOPNO = "edtShopNo";
    private static final String EDTCOMMERCIALSPACENO = "edtCommercialSpaceNo";
    private static final String TVBANKLOAN = "tvBankLoan";

    // PRICE


    private static final String PRICEGET = "";
    private static final String MAINTENANCEGET = "maintenanceget";
    private static final String BOOKINGAMOUNTGET = "bookingamountget";
    private static final String DURATIONOFRENTAGGET = "durationofrentagget";
    private static final String MONTHOFNOTICEGET = "monthofnoticeget";
    private static final String SECURITYDEPOSITESPINGET = "securitydepositspinget";
    private static final String MAINTEANCEMONTHLYSPINGFET = "maintenancemonthlyspinget";
    private static final String OWNERSHIPSPINGET = "ownweshipspinget";
    private static final String TYPEOFFOOD = "typeoffood";
    private static final String PRICESTEP = "pricestep";
    private static final String FACILITYINCLUDED = "facilityincluded";
    private static final String WEEKDAYS = "weekdays";
    private static final String WEEKENDS = "weeends";
    private static final String EARLYLIVINGCHARGESGET = "earlylivingchargesget";
    private static final String CONTRACTDURATIONGET = "contractdurationget";
    private static final String ANNUALDUESPAYABLEGET = "annualduespayableget";
    private static final String DEPOSITEAGET = "depositeaget";
    private static final String NEWPRICE = "newprice";
    private static final String INSTANCE = "instance";
    private static final String IMAGEPRESENT = "imagepresent";
    private static final String MOBILE = "mobile";
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String LEMAIL = "lemail";
    private static final String DATE = "date";
    private static final String SUBSCRIPTION = "subscription";
    private static final String POSITON = "position";
    private static final String OFFER = "offer";




    // FEATURES



    private static final String FACINGSPINGET = "facingspinget";
    private static final String UNITSPINGET = "unitspinget";
    private static final String TYPEOFFLOORINGSPINGET = "typeofflooringspinget";
    private static final String POWERBACKUPSPINGET = "powerbackupspinget";
    private static final String LASTTIMESPINGET = "lasttimespinget";
    private static final String PET = "pet";
    private static final String VISITOR = "visiter";
    private static final String SMOKING = "smoking";
    private static final String ALCOHOL = "alcohol";
    private static final String EVENT = "event";
    private static final String ANEMITIESITEM = "anemitiesitem";
    private static final String MOREANEMITIESITEM = "moreanemitiesitem";
    private static final String WATERSORCEITEM = "watersourceitem";
    private static final String OVERLOOKINGITEM = "overlookingitem";
    private static final String SOMEFEATUREITEM = "somefeatureitem";
    private static final String BYRESITEM = "byersitem";
    private static final String TIMEITEMS = "timeitems";
    private static final String WIDTHFACINGGET = "widthfacingget";
    private static final String DESCRIPTIONGET = "descriptionget";
    private static final String BOUNDRYWALL = "boundrywall";
    private static final String IMAGE = "image";


    //property id
    private static final String PROPERTYID = "propertyid";
    private static final String SHORTLISTEDVALUE = "value";






    private ListingData(Context context) {
        // Specify the application context
        mContext = context;
        // Get the request queue
    }

    public static synchronized ListingData getInstance(Context context) {
        // If Instance is null then initialize new Instance
        if (mInstance == null) {
            mInstance = new ListingData(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }



    public boolean basicdetails(String usertype, String type, String category, String categorytype, String agrement, String pgavailablefor, String pgshareprivate, String sharingspinnumber, String propertylistfor, String willing,String  pgsutablefor) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERTYPE, usertype);
        editor.putString(TYPE, type);
        editor.putString(CATEGORY, category);
        editor.putString(CATEGORYTYPE, categorytype);
        editor.putString(AGREMENT, agrement);
        editor.putString(PGAVAILABLEFOR, pgavailablefor);
        editor.putString(PGSHAREPRIVATE, pgshareprivate);
        editor.putString(SHARINGSPINNUMBER, sharingspinnumber);
        editor.putString(PROPERTYLISTFOR, propertylistfor);
        editor.putString(WILLING, willing);
        editor.putString(PGSUITABLEFOR, pgsutablefor);


        editor.apply();
        editor.commit();
        return true;
    }


    public String usertype(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERTYPE, null);
    }
    public String type(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TYPE, null);
    }
    public String category(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CATEGORY, null);
    }
    public String categorytype(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CATEGORYTYPE, null);
    }
    public String agrement(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AGREMENT, null);
    }
    public String pavailabefor(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PGAVAILABLEFOR, null);
    }
    public String pgshareprivate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PGSHAREPRIVATE, null);
    }
    public String sharingspinnumber(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SHARINGSPINNUMBER, null);
    }
    public String propertylistfor(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROPERTYLISTFOR, null);
    }
    public String willing(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(WILLING, null);
    }
    public String pgsuitablefor(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PGSUITABLEFOR, null);
    }









    // 2nd Page Location and project name

    public boolean addlocation(String state,String city, String address, String locality, String projectname){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STATE, state);
        editor.putString(CITY, city);
        editor.putString(ADDRESS, address);
        editor.putString(LOCALITY, locality);
        editor.putString(PROJECTNAME, projectname);

        editor.apply();
        editor.commit();

        return true;
    }

    public String state(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(STATE, null);
    }
    public String city(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CITY, null);
    }

    public String locality(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LOCALITY, null);
    }

    public String projectname(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROJECTNAME, null);
    }

    public String address(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ADDRESS, null);
    }



    public boolean setLatLog(String latlog) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LATLOG, latlog);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getLatLog(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LATLOG, null);
    }









    // Property Details



    public boolean addpropertydetails(String unit1, String unit2, String unit3, String availabilitydate,String homethings, String otherroom,
                                      String builtupa, String carpeta, String plota,
                                      String bed, String bath, String balconie,  String totalfloorget,
                                      String reservedpark,  String availabilityspinget,  String ageofpropertyspinget, String furnishedspinget,
                                      String possessionbyspinget, String noofroomspinget, String qualityratingspinget,
                                      String floorallowedspinget, String roomavailabespinget, String flattypespinget, String rera){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UNIT1, unit1);
        editor.putString(UNIT2, unit2);
        editor.putString(UNIT3, unit3);
        editor.putString(AVAILABILITYDATE, availabilitydate);
        editor.putString(HOMETHINGS, homethings);
        editor.putString(OTHERROOM, otherroom);
        editor.putString(BUILTUPA, builtupa);
        editor.putString(CARPETA, carpeta);
        editor.putString(PLOTA, plota);
        editor.putString(BED, bed);
        editor.putString(BATH, bath);
        editor.putString(BALCONIE, balconie);
        editor.putString(TOTALFLOORGET, totalfloorget);
        editor.putString(RESERVEDPARK, reservedpark);
        editor.putString(AVAILABILITYSPINGET, availabilityspinget);
        editor.putString(AGEOFPROPERTYSPINGET, ageofpropertyspinget);
        editor.putString(FURNISHEDSPINGET, furnishedspinget);
        editor.putString(POSSESSIONBYSPINGET, possessionbyspinget);
        editor.putString(NOOFROOM, noofroomspinget);
        editor.putString(QUALITYRATING, qualityratingspinget);
        editor.putString(FLOORALLOWED, floorallowedspinget);
        editor.putString(ROOMAVAILABLESPINGET, roomavailabespinget);
        editor.putString(FLATTYPESPINGET, flattypespinget);
        editor.putString(RERA, rera);

        editor.apply();
        editor.commit();
        return true;
    }

    public String unit1(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UNIT1, null);
    }

    public String getrera(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(RERA, null);
    }



    public String unit2(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UNIT2, null);
    }

    public String unit3(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UNIT3, null);
    }

    public String availabilitydate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AVAILABILITYDATE, null);
    }

    public String otherroom(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(OTHERROOM, null);
    }

    public String homethings(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(HOMETHINGS, null);
    }

    public String builtup(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BUILTUPA, null);
    }

    public String cartpeta(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CARPETA, null);
    }

    public String plota(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PLOTA, null);
    }

    public String totalfloorget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOTALFLOORGET, null);
    }

    public String reservedpark(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(RESERVEDPARK, null);
    }

    public String availabilityspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AVAILABILITYSPINGET, null);
    }

    public String ageofpropertyspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AGEOFPROPERTYSPINGET, null);
    }

    public String furnishedspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FURNISHEDSPINGET, null);
    }

    public String possessionbyspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(POSSESSIONBYSPINGET, null);
    }
    public String bed(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BED, null);
    }
    public String bath(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BATH, null);
    }
    public String balcoine(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BALCONIE, null);
    }

    public String noofroomspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NOOFROOM, null);
    }
    public String qualityratingspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(QUALITYRATING, null);
    }

    public String floorallowedspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FLOORALLOWED, null);
    }

    public String roomavailabespinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ROOMAVAILABLESPINGET, null);
    }

    public String flattypespinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FLATTYPESPINGET, null);
    }

    // Add property detail chnages

    public boolean addpropertydetailsChanges(String spiFlatTypeNew, String edtStudioAprtNo,
                                      String edtPentHouseNo, String edtRowHouseNo, String edtDuplexNo, String edtShopNo, String edtCommercialSpaceNo, String tvBankLoan){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SPIFLATTYPENEW, spiFlatTypeNew);
        editor.putString(EDTSTUDIOAPRTNO, edtStudioAprtNo);
        editor.putString(EDTPENTHOUSENO, edtPentHouseNo);
        editor.putString(EDTROWHOUSENO, edtRowHouseNo);
        editor.putString(EDTDUPLEXNO, edtDuplexNo);
        editor.putString(EDTSHOPNO, edtShopNo);
        editor.putString(EDTCOMMERCIALSPACENO, edtCommercialSpaceNo);
        editor.putString(TVBANKLOAN, tvBankLoan);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getSpiFlatTypeNew(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SPIFLATTYPENEW, null);
    }
    public String getEdtStudioAprtNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTSTUDIOAPRTNO, null);
    }
    public String getEdtPentHouseNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTPENTHOUSENO, null);
    }
    public String getEdtRowHouseNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTROWHOUSENO, null);
    }
    public String getEdtDuplexNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTDUPLEXNO, null);
    }
    public String getEdtShopNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTSHOPNO, null);
    }
    public String getEdtCommercialSpaceNo(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EDTCOMMERCIALSPACENO, null);
    }
    public String getTvBankLoan(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TVBANKLOAN, null);
    }




    //pricing




    public boolean addpricing(String priceget, String maintenanceget,
                              String bookingamountget, String durationofrentagget,
                              String monthofnoticeget,
                              String securitydepositspinget, String maintenancemonthlyspinget,
                              String ownweshipspinget, String typeoffood, String pricestep,
                              String facilityincluded, String weekdays, String weeends
    , String earlylivingchargesget,String contractdurationget, String annualduespayableget, String depositeaget) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PRICEGET, priceget);
        editor.putString(MAINTENANCEGET, maintenanceget);
        editor.putString(BOOKINGAMOUNTGET, bookingamountget);
        editor.putString(DURATIONOFRENTAGGET, durationofrentagget);
        editor.putString(MONTHOFNOTICEGET, monthofnoticeget);
        editor.putString(SECURITYDEPOSITESPINGET, securitydepositspinget);
        editor.putString(MAINTEANCEMONTHLYSPINGFET, maintenancemonthlyspinget);
        editor.putString(OWNERSHIPSPINGET, ownweshipspinget);
        editor.putString(TYPEOFFOOD, typeoffood);
        editor.putString(PRICESTEP, pricestep);
        editor.putString(FACILITYINCLUDED, facilityincluded);
        editor.putString(WEEKDAYS, weekdays);
        editor.putString(WEEKENDS, weeends);
        editor.putString(EARLYLIVINGCHARGESGET, earlylivingchargesget);
        editor.putString(CONTRACTDURATIONGET, contractdurationget);
        editor.putString(ANNUALDUESPAYABLEGET, annualduespayableget);
        editor.putString(DEPOSITEAGET, depositeaget);

        editor.apply();
        editor.commit();
        return true;
    }


    public String priceget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PRICEGET, null);
    }
    public String maintenanceget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MAINTENANCEGET, null);
    }
    public String bookingamountget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BOOKINGAMOUNTGET, null);
    }
    public String durationofrentagget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DURATIONOFRENTAGGET, null);
    }

    public String monthofnoticeget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MONTHOFNOTICEGET, null);
    }


    public String securitydepositspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SECURITYDEPOSITESPINGET, null);
    }
    public String maintenancemonthlyspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MAINTEANCEMONTHLYSPINGFET, null);
    }
    public String ownweshipspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(OWNERSHIPSPINGET, null);
    }
    public String typeoffood(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TYPEOFFOOD, null);
    }

    public String pricestep(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PRICESTEP, null);
    }
    public String facilityincluded(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACILITYINCLUDED, null);
    }
    public String weekdays(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(WEEKDAYS, null);
    }

    public String weeends(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(WEEKENDS, null);
    }

     public String earlylivingchargesget(){
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(EARLYLIVINGCHARGESGET, null);
        }
     public String contractdurationget(){
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(CONTRACTDURATIONGET, null);
        }

    public String annualduespayableget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ANNUALDUESPAYABLEGET, null);
    }
    public String depositeaget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DEPOSITEAGET, null);
    }




    //features


    public boolean addfeatures(String facingspinget, String unitspinget, String typeofflooringspinget, String powerbackupspinget,
                               String lasttimespinget, String pet, String visiter, String smoking, String alcohol,
                               String event, String anemitiesitem, String moreanemitiesitem, String watersourceitem, String overlookingitem,
                               String somefeatureitem, String byersitem, String timeitems ,String widthfacingget,
                               String descriptionget, String boundrywall, String image) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FACINGSPINGET, facingspinget);
        editor.putString(UNITSPINGET, unitspinget);
        editor.putString(TYPEOFFLOORINGSPINGET, typeofflooringspinget);
        editor.putString(POWERBACKUPSPINGET, powerbackupspinget);
        editor.putString(LASTTIMESPINGET, lasttimespinget);
        editor.putString(PET, pet);
        editor.putString(VISITOR, visiter);
        editor.putString(SMOKING, smoking);
        editor.putString(ALCOHOL, alcohol);
        editor.putString(EVENT, event);
        editor.putString(ANEMITIESITEM, anemitiesitem);
        editor.putString(MOREANEMITIESITEM, moreanemitiesitem);
        editor.putString(WATERSORCEITEM, watersourceitem);
        editor.putString(OVERLOOKINGITEM, overlookingitem);
        editor.putString(SOMEFEATUREITEM, somefeatureitem);
        editor.putString(BYRESITEM, byersitem);
        editor.putString(TIMEITEMS, timeitems);
        editor.putString(WIDTHFACINGGET, widthfacingget);
        editor.putString(DESCRIPTIONGET, descriptionget);
        editor.putString(BOUNDRYWALL, boundrywall);
        editor.putString(IMAGE, image);

        editor.apply();
        editor.commit();
        return true;
    }



    public String facingspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FACINGSPINGET, null);
    }

    public String unitspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(UNITSPINGET, null);
    }

    public String typeofflooringspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TYPEOFFLOORINGSPINGET, null);
    }

    public String powerbackupspinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(POWERBACKUPSPINGET, null);
    }

    public String lasttimespinget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LASTTIMESPINGET, null);
    }


    public String pet(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PET, null);
    }

    public String visiter(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(VISITOR, null);
    }

    public String smoking(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SMOKING, null);
    }

    public String alcohol(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ALCOHOL, null);
    }

    public String event(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EVENT, null);
    }

    public String anemitiesitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ANEMITIESITEM, null);
    }

    public String moreanemitiesitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MOREANEMITIESITEM, null);
    }

    public String watersourceitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(WATERSORCEITEM, null);
    }

    public String overlookingitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(OVERLOOKINGITEM, null);
    }

    public String somefeatureitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SOMEFEATUREITEM, null);
    }

    public String byersitem(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BYRESITEM, null);
    }

    public String timeitems(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TIMEITEMS, null);
    }

    public String widthfacingget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(WIDTHFACINGGET, null);
    }

    public String descriptionget(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DESCRIPTIONGET, null);
    }
    public String boundrywall(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BOUNDRYWALL, null);
    }
    public String getimage(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(IMAGE, null);
    }


    public boolean propertyid(String id) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROPERTYID, id);

        editor.apply();
        editor.commit();
        return true;
    }

    public String getpropertyid(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROPERTYID, null);
    }




    public boolean shortlistedvalue(String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHORTLISTEDVALUE, value);

        editor.apply();
        editor.commit();
        return true;
    }

    public String getshortlistedvalue(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SHORTLISTEDVALUE, null);
    }





    public boolean setnewprice (String newprice) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NEWPRICE, newprice);

        editor.apply();
        editor.commit();
        return true;
    }

    public String newprice(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NEWPRICE, null);
    }

    public boolean setinstrance (String instance) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(INSTANCE, instance);

        editor.apply();
        editor.commit();
        return true;
    }

    public String getinstance(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(INSTANCE, null);
    }

    public boolean Logout(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
        return true;
    }


    public boolean imagepresent(String imagepresent) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IMAGEPRESENT, imagepresent);

        editor.apply();
        editor.commit();
        return true;
    }

    public String getimagepresent(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(IMAGEPRESENT, null);
    }


    public boolean setmobile(String mobile) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MOBILE, mobile);

        editor.apply();
        editor.commit();
        return true;
    }
    public String getmobile1(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MOBILE, null);
    }

    public boolean setdatename(String date, String fname, String lname, String email) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE, date);
        editor.putString(FNAME, fname);
        editor.putString(LNAME, lname);
        editor.putString(LEMAIL, email);

        editor.apply();
        editor.commit();
        return true;
    }
    public String getdate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATE, null);
    }

    public String getfname(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FNAME, null);
    }

    public String getlname(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LNAME, null);
    }

    public String getlemail(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LEMAIL, null);
    }


    public boolean setsubscription(String subscription) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION, subscription);


        editor.apply();
        editor.commit();
        return true;
    }

    public String getsubscription(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SUBSCRIPTION, null);
    }


    public boolean setposition(LatestDataModel position) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POSITON, String.valueOf(position));


        editor.apply();
        editor.commit();
        return true;
    }


    public String getposition(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(POSITON, null);
    }




    public boolean setOfferPrice(String offer) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(OFFER, offer);
        editor.apply();
        editor.commit();
        return true;
    }


    public String getOfferPrice(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(OFFER, "");
    }



}
