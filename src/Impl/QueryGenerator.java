/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Impl;

/**
 *
 * @author Gianmarco
 */
public class QueryGenerator {
    //Query is generated depending on the choice the User made
    static public String resQuery(String category, String comp){
        String query;
        if(category.equals("TUTTE")){
            if(comp.equals("Incassi")){
                query="SELECT ACQDATA as DATA, SUM(settore.prezzo) as QTA FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore JOIN EVENTO on settore.idevento=evento.id \n" +
                "WHERE STATO='ACQUISTATO' AND ACQDATA BETWEEN ? AND ? GROUP BY ACQDATA ORDER BY QTA";
                
            }
            else{
                query="SELECT ACQDATA as DATA, count(biglietto.id) as QTA FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore JOIN EVENTO on settore.idevento=evento.id \n" +
                "WHERE STATO='ACQUISTATO' AND ACQDATA BETWEEN ? AND ? GROUP BY ACQDATA ORDER BY QTA";
            }
        }
        else{
            if(comp.equals("Incassi")){
                query="SELECT ACQDATA as DATA, SUM(settore.prezzo) as QTA FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore JOIN EVENTO on settore.idevento=evento.id \n" +
                "WHERE STATO='ACQUISTATO' AND TIPO=? AND ACQDATA BETWEEN ? AND ? GROUP BY ACQDATA ORDER BY QTA";
            }
            else{
                query="SELECT ACQDATA as DATA, count(biglietto.id) as QTA FROM BIGLIETTO JOIN SETTORE on biglietto.settoreid=settore.idsettore JOIN EVENTO on settore.idevento=evento.id \n" +
                "WHERE STATO='ACQUISTATO' AND TIPO=? AND ACQDATA BETWEEN ? AND ? GROUP BY ACQDATA ORDER BY QTA";
            }
        }
        return query;
    }
}
