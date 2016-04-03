
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/*
 players.add(new Player("Semenko", "EDM", 4, 12));
 players.add(new Player("Lemieux", "PIT", 45, 54));
 players.add(new Player("Kurri",   "EDM", 37, 53));
 players.add(new Player("Yzerman", "DET", 42, 56));
 players.add(new Player("Gretzky", "EDM", 35, 89));
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new StubReader();
    Player firstPlayer;
    Player secondPlayer;

    @Before
    public void setUp() {
        stats = new Statistics(this.readerStub);
        firstPlayer = this.readerStub.getPlayers().get(0);
        secondPlayer = this.readerStub.getPlayers().get(1);
    }

    @Test
    public void testSearchFound() {
        assertEquals(stats.search("Semenko").getName(), firstPlayer.getName());
        assertEquals(stats.search("Lemieux").getName(), secondPlayer.getName());
    }

    @Test
    public void testSearchNotFound() {
        assertNull(stats.search("Kissa"));
    }

    @Test
    public void testTeamFound() {
        assertEquals(stats.team("EDM").size(), 3);
    }

    @Test
    public void testTeamNotFound() {
        assertTrue(stats.team("Kissa").isEmpty());
    }

    @Test
    public void testZeroTopScorers() {
        assertTrue(stats.topScorers(0).isEmpty());
    }

    @Test
    public void testTopScorers() {
        assertEquals(stats.topScorers(3).size(), 3);
    }

    @Test
    public void returnTopScorersInCorrectOrder() {
        List<Player> topScores = stats.topScorers(5);
        assertFalse(topScores.isEmpty());
        assertEquals(5, topScores.size());
        assertEquals(topScores.get(0).getName(), "Gretzky");
        assertEquals(topScores.get(1).getName(), "Lemieux");
        assertEquals(topScores.get(2).getName(), "Yzerman");
        assertEquals(topScores.get(3).getName(), "Kurri");
        assertEquals(topScores.get(4).getName(), "Semenko");

    }
}
