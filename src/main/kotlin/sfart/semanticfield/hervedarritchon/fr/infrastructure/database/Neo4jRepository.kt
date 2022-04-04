package sfart.semanticfield.hervedarritchon.fr.infrastructure.database

import java.sql.DriverManager


class Neo4jRepository {
    fun read(key: String){
        // Connecting
        DriverManager.getConnection("jdbc:neo4j:bolt://localhost", "neo4j", "s3cr3t").use { connection ->
            connection.prepareStatement("MATCH (u:User)-[:FRIEND]->(f:User) WHERE u.name = $0 RETURN f.name, f.age").use { stmt ->
                stmt.setString(0, "John")
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        println("Friend: " + rs.getString("f.name") + " is " + rs.getInt("f.age"))
                    }
                }
            }
        }
    }

    fun write(){
        // Connecting
        DriverManager.getConnection("jdbc:neo4j:bolt://localhost", "neo4j", "s3cr3t").use { connection ->
            connection.prepareStatement("MATCH (u:User)-[:FRIEND]->(f:User) WHERE u.name = $0 RETURN f.name, f.age").use { stmt ->
                stmt.setString(0, "John")
                stmt.executeQuery().use { rs ->
                    while (rs.next()) {
                        println("Friend: " + rs.getString("f.name") + " is " + rs.getInt("f.age"))
                    }
                }
            }
        }
    }

}