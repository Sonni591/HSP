package de.oth.hsp.common.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractModelDesc {
    private Map<String, EntryDesc> entryMap = new LinkedHashMap<>();

    protected void register(EntryDesc entry) {
        entryMap.put(entry.getName(), entry);
    }

    public List<EntryDesc> getEntries() {
        return new ArrayList<>(entryMap.values());
    }

    public boolean contains(String name) {
        return entryMap.containsKey(name);
    }
}
