package br.com.mlsa.doarsangue.fragments.dummysol;

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
public class DummySolicitacao {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummySolicitacaoItem> ITEMS = new ArrayList<DummySolicitacaoItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummySolicitacaoItem> ITEM_MAP = new HashMap<String, DummySolicitacaoItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummySolicitacaoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummySolicitacaoItem createDummyItem(int position) {
        return new DummySolicitacaoItem(String.valueOf(position), "Solicitação " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Solicitação: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummySolicitacaoItem {
        public final String id;
        public final String content;
        public final String details;

        public DummySolicitacaoItem(String id, String content, String details) {
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
