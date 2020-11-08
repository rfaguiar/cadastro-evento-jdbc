package com.evento.dao;

import com.evento.model.Evento;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EventoDAOTest {

    private static Connection connection;

    @BeforeClass
    public static void iniciarClasse() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cadastroevento?useTimezone=true&serverTimezone=UTC",
                "root",
                "my-secret-pw");
    }

    @AfterClass
    public static void encerrarClasse() throws SQLException {
        connection.close();
    }


    @Test
    public void crud() {
        var evento = new Evento(null, "Notebook", new Date());

        //Criando a instância do DAO.
        var eventoDAO = new EventoDAO(connection);

        //Fazendo a isnerção e recuperando o identificador.
        var idEvento = eventoDAO.salvar(evento);
        assertNotNull("Identificador foi retornado como NULL.", idEvento);

        //Verificando se o registro realmente foi para o banco de dados.
        evento = eventoDAO.buscar(idEvento);
        assertNotNull("Evento nulo.", evento);

        //Atualizando o registro no banco de dados.
        var nomeAlterado = evento.getNome() + " alterado";
        evento.setNome(nomeAlterado);
        eventoDAO.atualizar(evento);

        //Verificando as atualização ocorreu com sucesso.
        evento = eventoDAO.buscar(idEvento);
        assertEquals("O nome não foi atualizado corretamente.", nomeAlterado, evento.getNome());

        //Removendo o registro.
        eventoDAO.deletar(idEvento);

        // O registro não existe mais. O método "buscar" deve retornar nulo.
        evento = eventoDAO.buscar(evento.getId());
        assertNull("Evento ainda existe e não deveria.", evento);
    }
}
