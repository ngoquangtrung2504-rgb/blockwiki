package com.web.blockwiki.service;
import com.web.blockwiki.model.Block;
import com.web.blockwiki.repository.BlockRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Transactional
    public Block createBlock(String name, String content) {
        Block block = new Block(name, content);
        return blockRepository.save(block);
    }

    @Transactional
    public void connectBlocksBelow(String parentName, String childName) {
        Block parent = blockRepository.findByName(parentName);
        Block child = blockRepository.findByName(childName);

        if (parent != null && child != null) {
            parent.addBlockBelow(child);
            blockRepository.save(parent);
        }
    }

    @Transactional
    public void connectBlocksAbove(String childName, String parentName) {
        Block child = blockRepository.findByName(childName);
        Block parent = blockRepository.findByName(parentName);

        if (child != null && parent != null) {
            child.addBlockAbove(parent);
            blockRepository.save(child);
        }
    }

     @Transactional(readOnly = true)
    public Block getBlockByName(String name) {
        return blockRepository.findByName(name);
    }

    /** Lấy tất cả block low-level dưới block này */
    @Transactional(readOnly = true)
    public List<Block> getAllBlocksBelow(String name) {
        return blockRepository.findAllBelow(name);
    }

    /** Lấy tất cả block high-level trên block này */
    @Transactional(readOnly = true)
    public List<Block> getAllBlocksAbove(String name) {
        return blockRepository.findAllAbove(name);
    }
}