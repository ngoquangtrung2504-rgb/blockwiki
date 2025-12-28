package com.web.blockwiki.model;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.*;

@Node
public class Block {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String content;

    // Nodes phía dưới (low-level)
    @Relationship(type = "LOW_LEVEL")
    private List<Block> blocksAbove = new ArrayList<>();

    @Relationship(type = "LOW_LEVEL")
    private List<Block> blocksBelow = new ArrayList<>();

        // Constructor
    public Block(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Block() {}
        // Thêm quan hệ "above"
    public void addBlockAbove(Block block) {
        if (!blocksAbove.contains(block)) {
            blocksAbove.add(block);
        }
    }
    
    // Thêm quan hệ "below"
    public void addBlockBelow(Block block) {
        if (!blocksBelow.contains(block)) {
            blocksBelow.add(block);
        }
    }

    // Xóa quan hệ "above"
    public void removeBlockAbove(Block block) {
        blocksAbove.remove(block);
        block.blocksBelow.remove(this); // đồng bộ
    }

    // Xóa quan hệ "below"
    public void removeBlockBelow(Block block) {
        blocksBelow.remove(block);
        block.blocksAbove.remove(this); // đồng bộ
    }
}
