package com.web.blockwiki.repository;
import org.springframework.stereotype.Repository;

import com.web.blockwiki.model.Block;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

@Repository
public interface BlockRepository extends Neo4jRepository<Block, Long> {
    Block findByName(String name);
        // Tìm tất cả block đi xuống (low-level)
    @Query("MATCH (b:Block {name: $name})-[:BELOW*]->(child) RETURN child")
    List<Block> findAllBelow(String name);

    // Tìm tất cả block đi lên (high-level)
    @Query("MATCH (b:Block {name: $name})-[:ABOVE*]->(parent) RETURN parent")
    List<Block> findAllAbove(String name);
}
