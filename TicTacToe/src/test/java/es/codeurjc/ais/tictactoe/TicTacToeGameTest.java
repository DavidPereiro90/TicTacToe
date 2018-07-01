package es.codeurjc.ais.tictactoe;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;
import es.codeurjc.ais.tictactoe.TicTacToeGame.CellMarkedValue;
import org.hamcrest.core.IsInstanceOf;

public class TicTacToeGameTest {

    private Player jugador1, jugador2;
    private Connection con1, con2;
    private TicTacToeGame tictac;
    private int turno;

    @Before
    public void setUp() {
        tictac = new TicTacToeGame();
        con1 = mock(Connection.class);
        con2 = mock(Connection.class);

        tictac.addConnection(con1);
        tictac.addConnection(con2);

        jugador1 = new Player(1, "O", "Jugador1");
        jugador2 = new Player(2, "X", "Jugador2");

        turno = 1;
    }

    private void comprobarJoinGame() {
        reset(con1);
        reset(con2);
        tictac.addPlayer(jugador1);
        verify(con1).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(jugador1)));
        verify(con2).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItem(jugador1)));

        tictac.addPlayer(jugador2);
        verify(con1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1, jugador2)));
        verify(con2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugador1, jugador2)));
    }

    private void comprobarMark(int celda) {
        tictac.mark(celda);
        verify(con1, times(turno)).sendEvent(eq(EventType.MARK), argThat(new IsInstanceOf(CellMarkedValue.class)));
        verify(con2, times(turno)).sendEvent(eq(EventType.MARK), argThat(new IsInstanceOf(CellMarkedValue.class)));
        turno++;
    }

    @Test
    public void testGanadorJugador1() {
        comprobarJoinGame();

        assertTrue(tictac.checkTurn(1));
        comprobarMark(4);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(2);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(7);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(1);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(8);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(0);

        WinnerResult checkWinner = tictac.checkWinner();
        assertTrue(checkWinner.win);
    }        

    @Test
    public void testGanadorJugador2() {
        comprobarJoinGame();

        assertTrue(tictac.checkTurn(1));
        comprobarMark(4);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(1);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(7);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(2);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(5);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(0);

        WinnerResult checkWinner = tictac.checkWinner();
        assertTrue(checkWinner.win);
    }

    @Test
    public void testEmpate() {
        comprobarJoinGame();

        assertTrue(tictac.checkTurn(1));
        comprobarMark(5);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(1);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(2);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(8);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(4);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(3);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(7);

        assertTrue(tictac.checkTurn(2));
        comprobarMark(6);

        assertTrue(tictac.checkTurn(1));
        comprobarMark(0);

        boolean esEmpate = tictac.checkDraw();
        assertTrue(esEmpate);
    }

}
