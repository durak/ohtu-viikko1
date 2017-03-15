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
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVarastonSisallolla() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiAsetaNegatiivistaTilavuutta() {
        varasto = new Varasto(-5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiAsetaNegatiivistaTilavuuttaSisallolla() {
        varasto = new Varasto(-5, 5);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstuktoriEiAsetaNegatiivistaSaldoa() {
        varasto = new Varasto(10, -5);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuuttaSuurempiLisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenNegatiivisellaLuvullaEiMuutaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenYliSaldonPalauttaaKaikenJaTyhjentaaSaldon() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalautuuOikein() {
        varasto.lisaaVarastoon(8);
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
        
    }

}
