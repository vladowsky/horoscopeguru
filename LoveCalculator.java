package hr.horoskop.horoskop.controlor;

import android.content.Context;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import hr.horoskop.horoskop.model.Love;
import hr.horoskop.horoskop.model.LoveCalculatorDB;

/**
 * Created by Zoran on 20.5.2015..
 */
public class LoveCalculator {

    private LoveCalculatorDB loveDb;
    private Context context;
    private List<Love> loveList, categoryList;

    //Random number generator
    private int low = 0;
    private int high = 100;

    //Love instances
    Love loveOne1, loveOne2, loveOne3, loveOne4, loveOne5, loveOne6, loveOne7;
    Love loveTwo1, loveTwo2, loveTwo3;
    Love loveThree1,loveThree2, loveThree3, loveThree4;
    Love loveFour1, loveFour2, loveFour3;
    Love loveFive1,loveFive2, loveFive3, loveFive4, loveFive5 ;
    Love loveSix1, loveSix2, loveSix3, loveSix4;


    public LoveCalculator(Context context) {
        this.context = context;
    }

    public void initLove(){

        loveDb = new LoveCalculatorDB(context);

        addLoveData();

    }

    public Love calculateLove(){
        Love love = null;

        //Get list of all signs data
        loveList = loveDb.getAllLove();


        int random = randInt(0, 100);


        if(random >= 0 && random < 30){
          love = getLove("1");
        } else if(random >= 30 && random < 50){
            love = getLove("2");
        }
        else if(random >= 50 && random < 60){
            love = getLove("3");
        }
        else if(random >= 60 && random < 70){
            love = getLove("4");
        }
        else if(random >= 70 && random < 80){
            love = getLove("5");
        }
        else if(random >= 80){
            love = getLove("6");
        }

        love.setPercentage(random);


        return love;
    }

    private Love getLove(String category){

        categoryList = new LinkedList<Love>();

        for(int i=0;i<loveList.size();i++){
            if(loveList.get(i).getCategory().equals(category)){
                categoryList.add(loveList.get(i));
            }
        }

        int categoryRandom = randInt(1, categoryList.size());

        return categoryList.get(categoryRandom-1);
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    private void addLoveCategoryOne(){
        loveOne1 = new Love();
        loveOne1.setCategory("1");
        loveOne1.setText("Vi ste klasičan primjer izreke \"suprotnosti se privlače\". Imate različita interesovanja i suprotne stavove po skoro svim pitanjima. Iako gajite prave emocije jedno prema drugom, da biste prevazišli razlike i učinili vezu uspješnom moraćete provoditi više vremena zajedno i raditi više na opstanku vaše veze.");

        loveOne2 = new Love();
        loveOne2.setCategory("1");
        loveOne2.setText("Početni problemi su uzeli zamaha u vašoj vezi. Česte svađe i neslaganja uzeće svoj danak. Neslaganje i problemi koje možete imati su često zbog različitog pogleda na život i budućnost. Uložite ogroman trud i sitni plodovi veze bi mogli da se stvore.");

        loveOne3 = new Love();
        loveOne3.setCategory("1");
        loveOne3.setText("Jedno od vas ima naglašenu želju za dominacijom u ljubavnom odnosu, dok drugo ume da bude nesnalažljivo i plašljivo, ovakav spoj se smatra nažalost ne baš tako istrajnim i uspešnim, međutim, izuzeci postoje u zavisnosti od toga kako ćete se vi pojedinačno boriti za opstanak u ovoj vezi.");

        loveOne4 = new Love();
        loveOne4.setCategory("1");
        loveOne4.setText("Ovakva kombinacija u ljubavi često daje neusklađenu i neuravnoteženu vezu zbog činjenice da je jedno od vas uvijek brzopletno i napadno i da na drugo vrši snažan pritisak. Vezu može da vam spasi samo dublje promišljanje i smirenost. Nema predaje, nego samo pružanje ljubavi i kompromis.");

        loveOne5 = new Love();
        loveOne5.setCategory("1");
        loveOne5.setText("Ovakav par u ljubavi daje na žalost najčešće prolaznu vezu sa naglašenim akcentom avanturizma kako temelja ovakvog odnosa koji se nakon određenog vremena gubi. Međutim, postoje izuzeci koji pokazuju da, i pored mnogih neslaganja, ovakav spoj ipak uspijeva da odoli mnogim teškoćama, prevaziđe razlike i veza ipak opstane.");

        loveOne6 = new Love();
        loveOne6.setCategory("1");
        loveOne6.setText("Vaša veza i nije baš tako uspešna. Mada vam može pomoći ako se prvobitne razlike prevaziđu. Ne smijete biti nepromišljeni i živjeti ''od danas do sutra'', nego planirati i uvek misliti na buduće događaje.");

        loveOne7 = new Love();
        loveOne7.setCategory("1");
        loveOne7.setText("Ovakva veza i nije baš najsretnija i naročito preporučljiva iz razloga što može doći do velikih neslaganja usljed toga što ponekad ne pružate dovoljno pažnje, dok se to očekuje od svog partnera. Najčešće se problemi u vašoj vezi javljaju zbog sujetnosti.");

    }

    private void addLoveCategoryTwo(){
        loveTwo1 = new Love();
        loveTwo1.setCategory("2");
        loveTwo1.setText("Pokaže li ljubavni kalkulator ovaj procenat, izgledi za vašu vezu nisu sjajni. Ipak, to ne znači da se uz adekavatan trud veza neće izroditi u prelijepo iskustvo. Želite li da uspije, potrudite se da date priroritet jedno drugom. No, da biste spriječili bol, budite realni u očekivanjima, jer uvijek postoji šansa da se raziđete.");

        loveTwo2 = new Love();
        loveTwo2.setCategory("2");
        loveTwo2.setText("Vaša kombinacija daje na baš tako skladan ljubavni odnos, naročito zbog postojanja izvesnih ključnih razlika među vama u pogledu shvatanja bitnih stvari i stila života tako da ovakav spoj često može izazvati ozbiljna neslaganja i probleme. Ali, sa pevazilaženjem ovakvih izvesnih\n" +
                "razlika, ovaj spoj bi uz trud i zalaganje ipak mogao stvoriti jednu vrlo postojnu i uspešnu vezu.");

        loveTwo3 = new Love();
        loveTwo3.setCategory("2");
        loveTwo3.setText("Za ovakvu kombinaciju u vezi karakteristično je to da oba partnera nemaju dovoljno hrabrosti da učine prvi korak, prilično ste suzdržani i zatvoreni jedno prema drugom što u neretkim slučajevima rezultira lošom komunikacijom koja stvara probleme u vašem odnosu. Za ovakvu ljubavnu vezu gotovo da i nema pravila po pitanju dužine veze. Možete opstati jao kratko, a nekada to preraste u dugotrajnu vezu, što zavisi od vaše želje i pružene ljubavi.");

    }

    private void addLoveCategoryThree(){
        loveThree1 = new Love();
        loveThree1.setCategory("3");
        loveThree1.setText("Ljubavni kalkulator govori da stvari između vas dvoje ne teku tako glatko kako ste se nadali. Ipak, veže vas nevjerovatna struja privlačnosti i svkakako se ne trebate predati prerano. Uvijek je bolje kajati se za nešto što ste pokušali, nego za nešto što niste.");

        loveThree2 = new Love();
        loveThree2.setCategory("3");
        loveThree2.setText("Vaš spoj ukazuje na veoma strastvenu i žestoku ljubavnu vezu u kojoj su osećanja oba partnera vrlo eksplozivna i intezivna. Ljubavna veza sa ovom kombinacijom je veoma vatrena, s tim nedostatkom što u ljubavnom odnosu češće može doći do nerazumevanja i pojave veće ljubomore. Radite na suzbijanju toga neprijatelja i nećete imati problema.");

        loveThree3 = new Love();
        loveThree3.setCategory("3");
        loveThree3.setText("Vaša veza zna biti izuzetno problematična zbog evidentnih razlika u mišljenju koje kod vas postoje. Kod vas je većinom zastupljeno slaganje prevashodno na seksualnom planu, dok dublje povezanosti u većini slučajeva ili uopšte nema, ili je manje zastupljena. Međutim, ovo objašnjenje ne smete shvatiti kao pravilo, jer kompatibilnost u ljubavi zavisi i od mnogih drugih faktora i činilaca, tj. morate više konzumirati ljubav koja se ne zasniva samo na odnosima.");

        loveThree4 = new Love();
        loveThree4.setCategory("3");
        loveThree4.setText("Pozitivna strana ovakvog spoja u vezi je ta da vi ostvarujete izuzetno dobru komunikaciju , dok se negativna strana ogleda u tome što su kod vas izraženije nego kod drugih stresne situacije, pošto ste po prirodi i jedno i drugo nervozni. Ali unosite mnogo iskrenosti u vašu vezu, a to je i najbitnije za ljubav koja opstaje.");

    }

    private void addLoveCategoryFour(){
        loveFour1 = new Love();
        loveFour1.setCategory("4");
        loveFour1.setText("Ljubavni kalkulator vam govori da imate više od 60% šanse da vaša veza uspije. Veoma ste kompatibilni i rijetko će biti ikakvih razlika i nesuglasica u vašoj vezi. Nastavite li ovim putem svakako možete očekivati svetlu budućnost sa vašim partnerom, ako oboje istrajete do kraja i ne dozvolite da vas eventualne nesuglasice i problemi sputaju na vašem zajedničkom putu.");

        loveFour2 = new Love();
        loveFour2.setCategory("4");
        loveFour2.setText("Ovakva kombinacija u ljubavi ukazuje na jednu ''bombastičnu'' ljubav punu osećanja i nežnosti, tako da prestavlja jednu vrlo moćnu kombinaciju koja može izgraditi vrlo čvrstu i pouzdanu vezu. Loša strana ogleda se u tome što u ovakvoj vezi može doći do određenih problema usled loše komunikacije zbog same prirode karakternih osobina koje se poklapaju : težnja ka nezavisnosti, slobodi, prednjačenju i dominaciji u vezi.");

        loveFour3 = new Love();
        loveFour3.setCategory("4");
        loveFour3.setText("Ovakva veza i ovakav spoj može doneti probleme usled obostrane velike tvrdoglavosti. Ukoliko jedno od vas ne nauči u toku života kako da ''spusti loptu'' lako može doći do razlaza. Najbitnija stavka jeste pronaći zajednički jezik u ljubavi, jer i pored potencijalnih problema ova veza ima velike šanse za uspeh, jer je intezitet osećaja podjednako snažan i kod jednog i kod drugog partnera.");

    }

    private void addLoveCategoryFive(){
        loveFive1 = new Love();
        loveFive1.setCategory("5");
        loveFive1.setText("Čestitamo! Ljubavni kalkulator pokazuje da ste dosegli visok nivo ljubavne kompatibilnosti. No kako je ljubav poput ptice koja će umrijeti ukoliko je redovno ne hranite, budite svjesni činjenice da se čak i uspješne i dugotrajne ljubavi moraju redovno hraniti.");

        loveFive2 = new Love();
        loveFive2.setCategory("5");
        loveFive2.setText("Ovakva kombinacija daje veoma postojanu i dinamičnu ljubavnu vezu, tako da predstavlja primer veze koja ima mnogo potencijala da bude veoma uspešna i trajna. Ulažite u vezu svaki dio sebe i uživajte u onome što stvorite.");

        loveFive3 = new Love();
        loveFive3.setCategory("5");
        loveFive3.setText("Za ovakvu kombinaciju u vezi može se reći da je gotovo savršena. Jedno od vas je osećajno i nežno što savršeno odgovara vašem odnosu koji teži ka harmoniji i stabilnosti. Vi kao par u kombinaciji sa ostalim harmoničnim aspektima možete i idete tim putem da izgradite neraskidivu ljubavnu vezu.");

        loveFive4 = new Love();
        loveFive4.setCategory("5");
        loveFive4.setText("Vi ste veoma zanimljiv par. I nakon dobrog upoznavanja uzajamno razumevanje i poverenje vam je na zavidnom nivou. Vama je lakše nego ostalima da pronađete zajednički jezik, jer u ljubavi delite iste vrednosti. Vaše želje, potrebe i ciljevi se u većini slučajeva poklapaju.");

        loveFive4 = new Love();
        loveFive4.setCategory("5");
        loveFive4.setText("Ovakva ljubavna veza dovodi do jedne veoma strastvene i žestoke ljubavi. Ono što je karakteristično za vašu vezu jeste slaganje na tjelesnom nivou koje je izraženije od slaganja na intelektualnom nivou, tako da, ukoliko se privlačnost među vama duže vreme održi, imate velike šanse za opstanak za čitav život, ukoliko ne dolazi do razlaza.");

        loveFive5 = new Love();
        loveFive5.setCategory("5");
        loveFive5.setText("U ovakvoj vezi veoma često dolazi do javljanja ogromne količine burnih osećanja i velikih strasti, tako da vi polako ali sigurno izgrađujete jednu čvrstu, stabilnu i trajnu vezu. Ne dozvoljavate da vašu sreću bilo šta naruši. Neprijatelje držite na distanci, a svoju ljubav čuvate najbolje što umete. Odlično vam za sada ide.");

    }

    private void addLoveCategorySix(){
        loveSix1 = new Love();
        loveSix1.setCategory("6");
        loveSix1.setText("Vi ste par iz raja! Stvoreni ste jedno za drugo. Stoga, ne tražite dalje, jer ste već pronašli ljubav svog života. Ukoliko već niste, uskoro ćete postati par na čijoj ljubavi bi svako mogao zavidjeti.");

        loveSix2 = new Love();
        loveSix2.setCategory("6");
        loveSix2.setText("Par iz snova, to su reči koje vas najbolje opisuju. Vi se jako dobro dopunjujete i privlačite na neobičan način. Ovakva veza je pravi pokazatelj kako se u ljubavi suprotnosti snažno privlače i usklađuju. Uživajte u tome što imate, a imate mnogo.");

        loveSix3 = new Love();
        loveSix3.setCategory("6");
        loveSix3.setText("Čestitamo! Vas dvoje ste veoma moćni kada je u pitanju ljubavni odnos. Vama često mnogi zavide zbog energičnosti i snažne emotivne i seksualne kompatibilnosti koja je među vama jače izražena nego kod drugih, na čemu vam sigurno mnogi zavide. Ovakav spoj smatra se ujedno i trajnijim.");

        loveSix4 = new Love();
        loveSix4.setCategory("6");
        loveSix4.setText("Vaš spoj u ljubavi daje energičan i podsticajan odnos. U ovakvom odnosu često postoji duga povezanost, a najčešće je i dugotrajne prirode. Takođe, za vas važi da ste par bez konkurencije i da bi vam svako mogao zavidjeti. Budućnost je pred vama.");

    }

    private void addLoveData() {

        addLoveCategoryOne();
        addLoveCategoryTwo();
        addLoveCategoryThree();
        addLoveCategoryFour();
        addLoveCategoryFive();
        addLoveCategorySix();




        loveList = loveDb.getAllLove();
        Log.d("TAG", "signsList size je: " + String.valueOf(loveList.size()));
        if(loveDb.getAllLove().size() == 0) {
            loveDb.addLove(loveOne1);
            loveDb.addLove(loveOne2);
            loveDb.addLove(loveOne3);
            loveDb.addLove(loveOne4);
            loveDb.addLove(loveOne5);
            loveDb.addLove(loveOne6);
            loveDb.addLove(loveOne7);
            loveDb.addLove(loveTwo1);
            loveDb.addLove(loveTwo1);
            loveDb.addLove(loveTwo2);
            loveDb.addLove(loveTwo3);
            loveDb.addLove(loveThree1);
            loveDb.addLove(loveThree2);
            loveDb.addLove(loveThree3);
            loveDb.addLove(loveThree4);
            loveDb.addLove(loveFour1);
            loveDb.addLove(loveFour2);
            loveDb.addLove(loveFour3);
            loveDb.addLove(loveFive1);
            loveDb.addLove(loveFive2);
            loveDb.addLove(loveFive3);
            loveDb.addLove(loveFive4);
            loveDb.addLove(loveFive5);
            loveDb.addLove(loveSix1);
            loveDb.addLove(loveSix2);
            loveDb.addLove(loveSix3);
            loveDb.addLove(loveSix4);
        }

    }
}
