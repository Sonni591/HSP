package de.oth.hsp.common.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractModelDesc {
    private Map<String, EntryDesc> entryMap = new LinkedHashMap<>();

    protected void register(String name, EntryDesc entry) {
        entryMap.put(name, entry);
    }

    public EntryDesc getEntry(String name) {
        return entryMap.get(name);
    }

    public boolean contains(String name) {
        return entryMap.containsKey(name);
    }

    public List<EntryDesc> getEntries() {
        return new ArrayList<>(entryMap.values());
    }
}
