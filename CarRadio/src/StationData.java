/*
 * StationData.java
 *
 * Version:
 *  $Id: StationData.java,v 1.1 2014/02/11 02:56:21 txw6529 Exp $
 * Revisions:
 *  $Log: StationData.java,v $
 *  Revision 1.1  2014/02/11 02:56:21  txw6529
 *  CarRadio Project
 *
 *
 */

import java.util.HashMap;

/**
 * Represents station data for a localized geographic region.
 *
 * @author Matthew Fluet (mtf)
 */
public class StationData {
    
    private HashMap<Integer,String> amStationData;
    private HashMap<Integer,String> fmStationData;
    
    private StationData() {
        amStationData = new HashMap<Integer,String>();
        fmStationData = new HashMap<Integer,String>();
    }
    
    
    private static StationData mkBostonMA() {
        StationData sd = new StationData();
        sd.amStationData.put(550,"WDDZ");
        sd.amStationData.put(580,"WTAG");
        sd.amStationData.put(590,"WEZE");
        sd.amStationData.put(650,"WSRO");
        sd.amStationData.put(680,"WRKO");
        sd.amStationData.put(740,"WJIB");
        sd.amStationData.put(790,"WSKO");
        sd.amStationData.put(800,"WNNW");
        sd.amStationData.put(850,"WEEI");
        sd.amStationData.put(890,"WAMG");
        sd.amStationData.put(900,"WSNH");
        sd.amStationData.put(950,"WROL");
        sd.amStationData.put(980,"WCAP");
        sd.amStationData.put(990,"WALE");
        sd.amStationData.put(1000,"WCMX");
        sd.amStationData.put(1030," WBZ");
        sd.amStationData.put(1060,"WBIX");
        sd.amStationData.put(1090,"WILD");
        sd.amStationData.put(1110,"WCEC");
        sd.amStationData.put(1120,"WBNW");
        sd.amStationData.put(1150,"WTTT");
        sd.amStationData.put(1200,"WKOX");
        sd.amStationData.put(1230,"WESX");
        sd.amStationData.put(1240,"WOON");
        sd.amStationData.put(1260,"WMKI");
        sd.amStationData.put(1290,"WRNI");
        sd.amStationData.put(1300,"WJDA");
        sd.amStationData.put(1310,"WORC");
        sd.amStationData.put(1320,"WARL");
        sd.amStationData.put(1330,"WRCA");
        sd.amStationData.put(1360,"WLYN");
        sd.amStationData.put(1380,"WNRI");
        sd.amStationData.put(1390,"WPLM");
        sd.amStationData.put(1400,"WLLH");
        sd.amStationData.put(1410,"WMSX");
        sd.amStationData.put(1430,"WXKS");
        sd.amStationData.put(1450,"WNBP");
        sd.amStationData.put(1460,"WBET");
        sd.amStationData.put(1470,"WAZN");
        sd.amStationData.put(1490,"WMRC");
        sd.amStationData.put(1510,"WWZN");
        sd.amStationData.put(1530,"WVBF");
        sd.amStationData.put(1550,"WNTN");
        sd.amStationData.put(1570,"WNSH");
        sd.amStationData.put(1590,"WSMN");
        sd.amStationData.put(1600,"WUNR");
        sd.fmStationData.put(88100,"WMBR");
        sd.fmStationData.put(88300,"WIQH");
        sd.fmStationData.put(88700,"WJMF");
        sd.fmStationData.put(88900,"WERS");
        sd.fmStationData.put(89100,"WHAB");
        sd.fmStationData.put(89700,"WGBH");
        sd.fmStationData.put(90300,"WZBC");
        sd.fmStationData.put(90500,"WICN");
        sd.fmStationData.put(90900,"WBUR");
        sd.fmStationData.put(91300,"WDJM");
        sd.fmStationData.put(91500,"WMFO");
        sd.fmStationData.put(91700,"WMWM");
        sd.fmStationData.put(91900,"WUMB");
        sd.fmStationData.put(92500,"WXRV");
        sd.fmStationData.put(92900,"WBOS");
        sd.fmStationData.put(93300,"WSNE");
        sd.fmStationData.put(93700,"WQSX");
        sd.fmStationData.put(94100,"WHJY");
        sd.fmStationData.put(94500,"WJMN");
        sd.fmStationData.put(95300,"WHRB");
        sd.fmStationData.put(95500,"WBRU");
        sd.fmStationData.put(95900,"WATD");
        sd.fmStationData.put(96900,"WTKK");
        sd.fmStationData.put(97700,"WBOT");
        sd.fmStationData.put(98500,"WBMX");
        sd.fmStationData.put(99100,"WPLM");
        sd.fmStationData.put(99500,"WKLB");
        sd.fmStationData.put(99900,"WHHB");
        sd.fmStationData.put(100100,"WBRS");
        sd.fmStationData.put(100700,"WZLX");
        sd.fmStationData.put(101500,"WWBB");
        sd.fmStationData.put(101700,"WFNX");
        sd.fmStationData.put(102100,"WSAK");
        sd.fmStationData.put(102500,"WCRB");
        sd.fmStationData.put(103300,"WODS");
        sd.fmStationData.put(104100,"WBCN");
        sd.fmStationData.put(104900,"WRBB");
        sd.fmStationData.put(105700,"WROR");
        sd.fmStationData.put(106300,"WHOB");
        sd.fmStationData.put(106700,"WMJX");
        sd.fmStationData.put(107300,"WAAF");
        sd.fmStationData.put(107900,"WXKS");
        return sd;
    }
    /**
     * Station data for Boston, MA.
     */
    public static StationData BostonMA = mkBostonMA();
    
    
    private static StationData mkDeathValleyCA() {
        StationData sd = new StationData();
        return sd;
    }
    /**
     * Station data for DeathValley, CA.
     */
    public static StationData DeathValleyCA = mkDeathValleyCA();


    private static StationData mkNewYorkNY() {
        StationData sd = new StationData();
        sd.amStationData.put(570,"WMCA");
        sd.amStationData.put(620,"WSNR");
        sd.amStationData.put(660,"WFAN");
        sd.amStationData.put(710," WOR");
        sd.amStationData.put(740,"WGSM");
        sd.amStationData.put(770,"WABC");
        sd.amStationData.put(820,"WNYC");
        sd.amStationData.put(880,"WCBS");
        sd.amStationData.put(910,"WRKL");
        sd.amStationData.put(930,"WPAT");
        sd.amStationData.put(970,"WWDJ");
        sd.amStationData.put(1010,"WINS");
        sd.amStationData.put(1050,"WEPN");
        sd.amStationData.put(1070,"WKMB");
        sd.amStationData.put(1100,"WHLI");
        sd.amStationData.put(1130,"WBBR");
        sd.amStationData.put(1160,"WVNJ");
        sd.amStationData.put(1170,"WWTR");
        sd.amStationData.put(1190,"WLIB");
        sd.amStationData.put(1230,"WFAS");
        sd.amStationData.put(1240,"WGBB");
        sd.amStationData.put(1250,"WMTR");
        sd.amStationData.put(1280,"WADO");
        sd.amStationData.put(1300,"WRCR");
        sd.amStationData.put(1310,"WXMC");
        sd.amStationData.put(1330,"WWRV");
        sd.amStationData.put(1350,"WNLK");
        sd.amStationData.put(1380,"WKDM");
        sd.amStationData.put(1400,"WSTC");
        sd.amStationData.put(1410,"WHTG");
        sd.amStationData.put(1420,"WLNA");
        sd.amStationData.put(1430,"WNSW");
        sd.amStationData.put(1440,"WNYG");
        sd.amStationData.put(1450,"WCTC");
        sd.amStationData.put(1460,"WVOX");
        sd.amStationData.put(1480,"WZRC");
        sd.amStationData.put(1490,"WGCH");
        sd.amStationData.put(1500,"WGHT");
        sd.amStationData.put(1520,"WTHE");
        sd.amStationData.put(1530,"WJDM");
        sd.amStationData.put(1560,"WQEW");
        sd.amStationData.put(1600,"WWRL");
        sd.amStationData.put(1660,"WWRU");
        sd.fmStationData.put(88100,"WCWP");
        sd.fmStationData.put(88300,"WBGO");
        sd.fmStationData.put(88500,"WKWZ");
        sd.fmStationData.put(88700,"WRHU");
        sd.fmStationData.put(88900,"WSIA");
        sd.fmStationData.put(89100,"WNYU");
        sd.fmStationData.put(89300,"WCNJ");
        sd.fmStationData.put(89500,"WSOU");
        sd.fmStationData.put(89900,"WKCR");
        sd.fmStationData.put(90300,"WHCR");
        sd.fmStationData.put(90500,"WJSV");
        sd.fmStationData.put(90700,"WFUV");
        sd.fmStationData.put(90900,"WKRB");
        sd.fmStationData.put(91100,"WFMU");
        sd.fmStationData.put(91500,"WNYE");
        sd.fmStationData.put(92300,"WXRK");
        sd.fmStationData.put(92700,"WZAA");
        sd.fmStationData.put(93100,"WPAT");
        sd.fmStationData.put(93500,"WRTN");
        sd.fmStationData.put(93900,"WNYC");
        sd.fmStationData.put(94300,"WJLK");
        sd.fmStationData.put(94700,"WFME");
        sd.fmStationData.put(95500,"WPLJ");
        sd.fmStationData.put(95900,"WEFX");
        sd.fmStationData.put(96300,"WQXR");
        sd.fmStationData.put(96700,"WKHL");
        sd.fmStationData.put(97100,"WQHT");
        sd.fmStationData.put(97900,"WSKQ");
        sd.fmStationData.put(98300,"WKJY");
        sd.fmStationData.put(98700,"WRKS");
        sd.fmStationData.put(99100,"WAWZ");
        sd.fmStationData.put(99500,"WBAI");
        sd.fmStationData.put(100300,"WHTZ");
        sd.fmStationData.put(101100,"WCBS");
        sd.fmStationData.put(101900,"WQCD");
        sd.fmStationData.put(102300,"WBAB");
        sd.fmStationData.put(102700,"WNEW");
        sd.fmStationData.put(103500,"WKTU");
        sd.fmStationData.put(103900,"WFAS");
        sd.fmStationData.put(104300,"WAXQ");
        sd.fmStationData.put(105100,"WWPR");
        sd.fmStationData.put(105500,"WDHA");
        sd.fmStationData.put(105900,"WCAA");
        sd.fmStationData.put(106300,"WHTG");
        sd.fmStationData.put(106700,"WLTW");
        sd.fmStationData.put(107100,"WXPK");
        sd.fmStationData.put(107500,"WBLS");
        return sd;
    }
    /**
     * Station data for New York, NY.
     */
    public static StationData NewYorkNY = mkNewYorkNY();


    private static StationData mkRochesterNY() {
        StationData sd = new StationData();
        sd.amStationData.put(950,"WROC");
        sd.amStationData.put(990,"WLGZ");
        sd.amStationData.put(1040,"WYSL");
        sd.amStationData.put(1140,"WCJW");
        sd.amStationData.put(1180,"WHSD");
        sd.amStationData.put(1240,"WGVA");
        sd.amStationData.put(1280,"WHTK");
        sd.amStationData.put(1310,"WRSB");
        sd.amStationData.put(1370,"WXXI");
        sd.amStationData.put(1420,"WACK");
        sd.amStationData.put(1460,"WHIC");
        sd.amStationData.put(1490,"WBTA");
        sd.amStationData.put(1550,"WCGR");
        sd.amStationData.put(1590,"WASB");
        sd.fmStationData.put(88100,"WFRW");
        sd.fmStationData.put(88300,"WCOU");
        sd.fmStationData.put(88500,"WRUR");
        sd.fmStationData.put(88900,"WCIY");
        sd.fmStationData.put(89100,"WBSU");
        sd.fmStationData.put(89300,"WMHN");
        sd.fmStationData.put(89700,"WITR");
        sd.fmStationData.put(90100,"WGMC");
        sd.fmStationData.put(90500,"WBER");
        sd.fmStationData.put(90700,"WGCC");
        sd.fmStationData.put(91500,"WXXI");
        sd.fmStationData.put(92500,"WBEE");
        sd.fmStationData.put(93300,"WBBF");
        sd.fmStationData.put(93700,"WCOV");
        sd.fmStationData.put(94100,"WZNE");
        sd.fmStationData.put(95100,"WFXF");
        sd.fmStationData.put(96500,"WCMF");
        sd.fmStationData.put(97900,"WPXY");
        sd.fmStationData.put(98900,"WBZA");
        sd.fmStationData.put(99700,"WZXV");
        sd.fmStationData.put(100500,"WVOR");
        sd.fmStationData.put(101300,"WRMM");
        sd.fmStationData.put(101700,"WFLK");
        sd.fmStationData.put(102100,"WJCA");
        sd.fmStationData.put(102300,"WISY");
        sd.fmStationData.put(102700,"WRCI");
        sd.fmStationData.put(103500,"WUUF");
        sd.fmStationData.put(103900,"WDKX");
        sd.fmStationData.put(104700,"WIRQ");
        sd.fmStationData.put(105500,"WMJQ");
        sd.fmStationData.put(105700,"WSAC");
        sd.fmStationData.put(105900,"WJZR");
        sd.fmStationData.put(106700,"WKGS");
        sd.fmStationData.put(107300,"WNVE");
        return sd;
    }
    /**
     * Station data for Rochester, NY.
     */
    public static StationData RochesterNY = mkRochesterNY();


    /**
     * Lookup a frequency.
     *
     * @param band The frequency band in which to lookup the frequency
     * {@code freq}.
     * @param freq A valid frequency in either the AM band 
     * (520kHz &ndash; 1610kHz, if {@code band} is 
     * {@link FreqBand#AM}) or the FM band (87900kHz &ndash; 107900kHz,
     * if {@code band} is {@link FreqBand#FM}).
     * @return The station id (as a non-{@code null} 
     * {@link java.lang.String String}) of the station broadcasting at
     * the frequency {@code freq}, or {@code null} if there is no
     * station broadcasting at the frequency {@code freq}.
     */
    public String lookupFreq(FreqBand band, int freq) {
        switch (band) {
        case AM: {
            if ((freq < FreqBand.AM.minFreq()) || (freq > FreqBand.AM.maxFreq())) {
                throw new IllegalArgumentException("StationData.lookupFreq: Frequency out of range.");
            }
            return amStationData.get(freq);
        }
        case FM: {
            if ((freq < FreqBand.FM.minFreq()) || (freq > FreqBand.FM.maxFreq())) {
                throw new IllegalArgumentException("StationData.lookupFreq: Frequency out of range.");
            }
            return fmStationData.get(freq);
        }
        }
        return null;
    }
}
