package com.web.blockwiki.controller;
import org.springframework.web.bind.annotation.*;
import com.web.blockwiki.service.*;
import com.web.blockwiki.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    // Tạo block
    @PostMapping
    public Block createBlock(@RequestParam String name, @RequestParam String content) {
        return blockService.createBlock(name, content);
    }

    // Kết nối block dưới
    @PostMapping("/connect")
    public void connectBlocksBelow(@RequestParam String parentName, @RequestParam String childName) {
        blockService.connectBlocksBelow(parentName, childName);
    }

    // Lấy block theo tên
    @GetMapping("/{name}")
    public Block getBlock(@PathVariable String name) {
        return blockService.getBlockByName(name);
    }

    // Lấy tất cả block dưới (low-level)
    @GetMapping("/{name}/below")
    public List<Block> getBlocksBelow(@PathVariable String name) {
        return blockService.getAllBlocksBelow(name);
    }

    // Lấy tất cả block trên (high-level)
    @GetMapping("/{name}/above")
    public List<Block> getBlocksAbove(@PathVariable String name) {
        return blockService.getAllBlocksAbove(name);
    }
}
