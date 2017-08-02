package br.com.mlsa.doarsangue.fragments.dummydoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyDoacao {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyDoacaoItem> ITEMS = new ArrayList<DummyDoacaoItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyDoacaoItem> ITEM_MAP = new HashMap<String, DummyDoacaoItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyDoacaoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyDoacaoItem createDummyItem(int position) {
        return new DummyDoacaoItem(String.valueOf(position), "Doação " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Doação: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyDoacaoItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyDoacaoItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
