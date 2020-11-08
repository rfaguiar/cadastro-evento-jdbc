package com.evento.dao;

import com.evento.model.Evento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class EventoDAO {

    private final Connection connection;

    public EventoDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer salvar(Evento evento) {
        var sql = "insert into evento (id, nome, data) values(null, ?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, new Date(evento.getData().getTime()));

             return preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
            throw new RuntimeException();
        }
    }

    public Integer atualizar(Evento evento) {
        var sql = "update evento set nome = ?, data = ? where id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, evento.getNome());
            preparedStatement.setDate(2, new Date(evento.getData().getTime()));
            preparedStatement.setInt(3, evento.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
            throw new RuntimeException();
        }
    }

    public Evento buscar(Integer id) {
        var sql = "select * from evento where id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);

            try (var resultSet = preparedStatement.executeQuery()){
                if (!resultSet.next()) {
                    return null;
                }
                return new Evento(id,
                        resultSet.getString("nome"),
                        resultSet.getDate("data"));
            }
        } catch (SQLException ignored) {
            throw new RuntimeException();
        }
    }

    public List<Evento> buscar() {
        var sql = "select * from evento";
        try (var preparedStatement = connection.prepareStatement(sql)){
            try (var resultSet = preparedStatement.executeQuery()){
                List<Evento> eventos = Collections.emptyList();
                while (resultSet.next()) {
                    eventos.add(
                            new Evento(
                                    resultSet.getInt("id"),
                                    resultSet.getString("nome"),
                                    resultSet.getDate("data")
                            )
                    );
                }
                return eventos;
            }
        } catch (SQLException ignored) {
            throw new RuntimeException();
        }
    }

    public Integer deletar(Integer id) {
        var sql = "delete from evento where id = ?";
        try (var preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException ignored) {
            throw new RuntimeException();
        }
    }
}
