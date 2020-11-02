package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto kelvotonVarasto;
    Varasto kuormitettuVarasto;
    Varasto kuormitettuKelvotonVarasto;
    Varasto kuormitettuVarastoNegatiivisellaSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        kelvotonVarasto = new Varasto(-5);
        kuormitettuVarasto = new Varasto(10, 2);
        kuormitettuKelvotonVarasto = new Varasto(0, 10);
        kuormitettuVarastoNegatiivisellaSaldolla = new Varasto(2, -2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(1000000, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoKelvottomanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriLuoVaraston() {
        assertEquals(2, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriLuoKelvottomanVaraston() {
        assertEquals(0, kuormitettuKelvotonVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoOnVahintaanNolla() {
        assertEquals(0, kuormitettuVarastoNegatiivisellaSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisataNegatiivistaSaldoa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
     @Test
    public void varastoonEiVoiLisataLiikaaTavaraa() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivistaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-4);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaEnempaaKuinSiellaOn() {
        varasto.lisaaVarastoon(6);
        varasto.otaVarastosta(7);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonMerkkijonoEsitysToimii() {
        varasto.lisaaVarastoon(4);
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

}