/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.codeurjc.ais.tictactoe;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class BoardTest {
    
    static Board tablero;
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        tablero = new Board();
    }
    
   @Test
    public void testGanadorJugadorUno() {

        marcarCelda(4, "0");
        marcarCelda(1, "X");
        marcarCelda(7, "O");
        marcarCelda(2, "X");
        marcarCelda(5, "0");
        marcarCelda(0, "X");

        int[] celdas = tablero.getCellsIfWinner("X");

        assertNotNull(celdas);
    }

    @Test
    public void testGanadorJugadorDos() {

        marcarCelda(4, "X");
        marcarCelda(2, "O");
        marcarCelda(7, "X");
        marcarCelda(1, "O");
        marcarCelda(8, "X");
        marcarCelda(0, "O");


        int[] celdas = tablero.getCellsIfWinner("O");

        assertNotNull(celdas);
    }

    @Test
    public void testEmpate() {
        marcarCelda(5, "X");
        marcarCelda(1, "O");
        marcarCelda(2, "X");
        marcarCelda(8, "O");
        marcarCelda(4, "X");
        marcarCelda(3, "O");
        marcarCelda(6, "X");
        marcarCelda(0, "O");
        marcarCelda(7, "X");

        boolean isDraw = tablero.checkDraw();
        assertTrue(isDraw);
    }

    private void marcarCelda(int cellId, String value) {
        TicTacToeGame.Cell cell = tablero.getCell(cellId);
        cell.value = value;
    }
    
}
