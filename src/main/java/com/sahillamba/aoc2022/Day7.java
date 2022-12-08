package com.sahillamba.aoc2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Day7 extends Solution {
    private static final String CMD_INDICATOR = "$";
    private static final String CD_DIR_UP = "..";

    private static final long TOTAL_DISK_SPACE = 70000000L;
    private static final long MIN_FREE_DISK_SPACE_REQUIRED = 30000000L;

    private final FSNode root;

    public Day7(String inputType, boolean debug) {
        super(7, inputType);
        root = new FSNode("root", FSNode.Type.Directory, null);
        createFileSystem(root);
        if (debug) {
            root.print();
        }
    }

    public String part1() {
        return String.valueOf(root.totalSizeOfDirectoriesLessThan100kBytes());
    }
    
    public String part2() {
        long unusedSpace = TOTAL_DISK_SPACE - root.children.get("/").sizeInBytes;
        long spaceToFree = MIN_FREE_DISK_SPACE_REQUIRED - unusedSpace;
        return String.valueOf(root.getSmallestDirectoryWithSizeGreaterThan(spaceToFree));
    }

    private void createFileSystem(FSNode root) {
        List<String> lines = reader.lines().collect(Collectors.toList());

        FSNode current = root;
        CmdProcessor processor = new CmdProcessor();
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).startsWith(CMD_INDICATOR)) {
                continue;
            }
            current = processor.process(lines, i, current);
        }
        root.updateDirectorySizes();
    }

    /**
     * Processor to process input commands such as "cd" and "ls".
     */
    static class CmdProcessor {
        public FSNode process(List<String> lines, int idx, FSNode current) {
            String[] parts = lines.get(idx).split("\\s");
            String cmd = parts[1];
            switch (cmd) {
                case "cd":
                    return cd(parts[2], current);
                case "ls":
                    return ls(lines, idx, current);
            }
            throw new IllegalArgumentException("could not process line: " + lines.get(idx));
        }

        private FSNode cd(String dirName, FSNode current) {
            if (dirName.equals(CD_DIR_UP)) {
                return current.parent;
            }
            return current.putDirectoryIfAbsent(dirName);
        }

        private FSNode ls(List<String> lines, int idx, FSNode current) {
            BiConsumer<String, FSNode> processDirOutput = (String lsOutput, FSNode node) -> {
                String[] lsOutputParts = lsOutput.split("\\s");
                String dirName = lsOutputParts[1];
                node.putDirectoryIfAbsent(dirName);
            };

            BiConsumer<String, FSNode> processFileOutput = (String lsOutput, FSNode node) -> {
                String[] lsOutputParts = lsOutput.split("\\s");
                long sizeInBytes = Long.parseLong(lsOutputParts[0]);
                String fileName = lsOutputParts[1];
                node.putFileIfAbsent(fileName, sizeInBytes);
            };

            String lsOutput = lines.get(++idx);
            while (!lsOutput.startsWith(CMD_INDICATOR)) {
                if (lsOutput.startsWith(FSNode.Type.Directory.value)) {
                    processDirOutput.accept(lsOutput, current);
                } else {
                    processFileOutput.accept(lsOutput, current);
                }
                if (++idx >= lines.size()) {
                    break;
                }
                lsOutput = lines.get(idx);
            }

            return current;
        }
    }

    /**
     * File System Node, may represent a Directory or a File (based on "type").
     */
    static class FSNode {
        String name;
        long sizeInBytes;
        Type type;

        FSNode parent;
        Map<String, FSNode> children;

        public FSNode(String name, Type type, FSNode parent) {
            this.name = name;
            this.sizeInBytes = 0;
            this.type = type;
            this.parent = parent;
            this.children = new HashMap<>();
        }

        public void print() {
            indentedPrint(0, children.get("/"));
        }

        /**
         * Adds directory under current node
         * @param dirName, directory name
         * @return FSNode, added directory reference
         */
        public FSNode putDirectoryIfAbsent(String dirName) {
            FSNode child = children.getOrDefault(dirName, new FSNode(dirName, Type.Directory, this));
            children.putIfAbsent(dirName, child);
            return child;
        }

        /**
         * Adds file under current node
         * @param fileName, file name
         * @return FSNode, added file reference
         */
        public FSNode putFileIfAbsent(String fileName, long sizeInBytes) {
            FSNode child = children.getOrDefault(fileName, new FSNode(fileName, Type.File, this));
            children.putIfAbsent(fileName, child);
            child.sizeInBytes = sizeInBytes;
            return child;
        }

        public void updateDirectorySizes() {
            getDirectorySizeRecursive(children.get("/"));
        }

        public long totalSizeOfDirectoriesLessThan100kBytes() {
            return totalSizeOfDirectoriesLessThan100kBytesRecursive(children.get("/"));
        }

        public long getSmallestDirectoryWithSizeGreaterThan(long spaceToFree) {
            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            addDirectoriesWithSizeGreaterThan(children.get("/"), spaceToFree, minHeap);
            return minHeap.peek();
        }

        private long getDirectorySizeRecursive(FSNode node) {
            if (node.type == Type.File) {
                return node.sizeInBytes;
            }
            AtomicLong sum = new AtomicLong();
            node.children.forEach((key, value) -> {
                sum.addAndGet(getDirectorySizeRecursive(value));
            });
            node.sizeInBytes = sum.get();
            return sum.get();
        }

        private long totalSizeOfDirectoriesLessThan100kBytesRecursive(FSNode node) {
            if (node.type == Type.File) {
                return 0;
            }
            AtomicLong sum = new AtomicLong();
            if (node.sizeInBytes <= 100000) {
                sum.addAndGet(node.sizeInBytes);
            }
            node.children.forEach((key, value) -> {
                sum.addAndGet(totalSizeOfDirectoriesLessThan100kBytesRecursive(value));
            });
            return sum.get();
        }

        private void addDirectoriesWithSizeGreaterThan(FSNode node, long spaceToFree, PriorityQueue<Long> minHeap) {
            if (node.type == Type.File) {
                return;
            }
            if (node.sizeInBytes >= spaceToFree) {
                minHeap.add(node.sizeInBytes);
            }
            node.children.forEach((key, value) -> {
                addDirectoriesWithSizeGreaterThan(value, spaceToFree, minHeap);
            });
        }

        private void indentedPrint(int depth, FSNode node) {
            for (int i = depth; i > 0; i--) {
                System.out.print(i == 1 ? "|- " : "   ");
            }
            System.out.printf("%s (%s, size = %d)%n", node.name, node.type.value, node.sizeInBytes);
            node.children.forEach((key, value) -> indentedPrint(depth + 1, value));
        }

        enum Type {
            Directory("dir"),
            File("file");

            final String value;

            Type(String value) {
                this.value = value;
            }
        }
    }
}
