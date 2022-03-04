package com.coinspy.telegram.message;

import com.coinspy.telegram.message.vulnerability.Vulnerability;
import com.coinspy.token.Token;
import com.coinspy.useful.Calculator;

import java.util.Map;

public class MessageCreator {
    enum Emoji {
        FIRE("\uD83D\uDD25"),
        WAVES("\uD83C\uDF0A"),
        NEWSPAPER("\uD83D\uDCF0"),
        MOON("\uD83C\uDF15"),
        CHART("\uD83D\uDCC8"),
        EXCHANGE("\uD83D\uDCB1"),
        INFORMATION("\uD83D\uDCDD"),
        LOCK("\uD83D\uDD12"),
        HOURGLASS("\uD629\u0000"),
        SHIELD("🛡️"),
        GREEN_CIRCLE("\uD83D\uDFE2"),
        YELLOW_CIRCLE("\uD83D\uDFE1"),
        RED_CIRCLE("\uD83D\uDD34"),
        WARNING("⚠");

        String value;

        Emoji(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    enum Sector {
        BASIC_SOCIAL_MEDIA(Emoji.NEWSPAPER + " <b>Basic social media:</b>"),
        LIQUIDITY_POOL(Emoji.WAVES + " <b>Liquidity Pool:</b>"),
        CHARTS(Emoji.CHART + " <b>Charts:</b>"),
        DEX(Emoji.EXCHANGE + " <b>Decentralize Exchange:</b>"),
        EXPLORER(Emoji.INFORMATION + " <b>Explorer:</b>"),
        PREMIUM_TOOLS(Emoji.MOON + " <b>Premium tools:</b>"),
        LOCK(Emoji.LOCK + " <b>Is liquidity locked?:</b>"),
        SECURITY(Emoji.SHIELD + " <b>Security: </b>");

        String value;

        Sector(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static final class MessageCreatorBuilder {
        private String twitterSearchUrl = "https://twitter.com/search?q=";
        private String redditSearchUrl = "https://reddit.com/search/?q=";
        private String fourChanSearchUrl = "https://boards.4channel.org/biz/catalog#s=";

        private final StringBuilder message ;
        private final Token token;

        private MessageCreatorBuilder(Token token) {
            this.token = token;
            this.message = new StringBuilder();
        }

        public static MessageCreatorBuilder builder(Token token) {
            return new MessageCreatorBuilder(token);
        }

        public MessageCreatorBuilder sector(Sector text){
            this.message.append(text);
            this.message.append(System.lineSeparator());
            return this;
        }

        public MessageCreatorBuilder link(String linkName, String link) {
            this.message.append("<a href=\"").append(link).append("\">").append(linkName).append("</a>");
            return this;
        }

        public MessageCreatorBuilder text(String text) {
            this.message.append(text);
            return this;
        }

        public MessageCreatorBuilder newLine() {
            this.message.append(System.lineSeparator());
            return this;
        }

        public MessageCreatorBuilder textLine(String text) {
            this.message.append(text);
            this.message.append(System.lineSeparator());
            return this;
        }

        public MessageCreatorBuilder emoji(Emoji emoji){
            this.message.append(emoji.toString());
            return this;
        }

        public MessageCreatorBuilder title(String title){
            return this.textLine(Emoji.FIRE + " <b>"+ title +"</b> " + Emoji.FIRE);
        }

        public MessageCreatorBuilder tokenInfo(){
            return this.textLine(String.format("Name: %s  |  Ticket: %s ",
                            token.getTokenName(), token.getTokenSymbol()))
                        .textLine(String.format("Total supply: %s",
                            token.getTotalSupply()));
        }

        public MessageCreatorBuilder socialMedia(){
            return this.sector(Sector.BASIC_SOCIAL_MEDIA)
                    .text("Twitter: ").link("NAME", twitterSearchUrl + token.getTokenName())
                    .text(" | ").link("TICKET", twitterSearchUrl + '$' + token.getTokenSymbol())
                    .newLine()
                    .text("Reddit: ").link("NAME", redditSearchUrl + token.getTokenName())
                    .text(" | ").link("TICKET", redditSearchUrl + '$' + token.getTokenSymbol())
                    .newLine()
                    .text("4chan: ").link("NAME", fourChanSearchUrl + token.getTokenName())
                    .text(" | ").link("TICKET", fourChanSearchUrl + '$' + token.getTokenSymbol())
                    .newLine();
        }

        public MessageCreatorBuilder liquidityPool(){
            return this.sector(Sector.LIQUIDITY_POOL)
                    .textLine(String.format("%s $%s, (%s of total supply)",
                            token.getLiquidityPair().getValue1(),
                            token.getLiquidityPair().getTokenSymbol1(),
                            Calculator.percent(token.getTotalSupply(), token.getLiquidityPair().getValue1())
                    ))
                    .textLine(String.format("%s $%s",
                            token.getLiquidityPair().getValue2(),
                            token.getLiquidityPair().getTokenSymbol2()));
        }

        public MessageCreatorBuilder dex(String buyUrl, String sellUrl, String infoUrl){
            return this.sector(Sector.DEX)
                    .text("Buy: ").link("LINK", buyUrl)
                    .newLine()
                    .text("Sell: ").link("LINK", sellUrl)
                    .newLine()
                    .text("Info: ").link("LINK", infoUrl)
                    .newLine();
        }

        public MessageCreatorBuilder charts(Map<String, String> nameUrl){
            this.sector(Sector.CHARTS);

            nameUrl.forEach((k, v) -> this.text(k)
                    .link("LINK", v)
                    .newLine());

            return this;
        }

        public MessageCreatorBuilder explorer(Map<String, String> nameUrl){
            this.sector(Sector.EXPLORER);

            nameUrl.forEach((k, v) -> this.text(k)
                    .link("LINK", v)
                    .newLine());

            return this;
        }

        public MessageCreatorBuilder lock(Map<String, String> nameUrl){
            this.sector(Sector.LOCK);

            nameUrl.forEach((k, v) -> this.text(k)
                    .link("LINK", v)
                    .newLine());

            return this;
        }

        public MessageCreatorBuilder security(){
            this.sector(Sector.SECURITY);

            if (token.getSecurity().isVerified()) {
                int amount = Vulnerability.possibleVulnerability(token.getSecurity().getCompilerVersion());
                String compilerVersion = Vulnerability.cutVersion(token.getSecurity().getCompilerVersion());

                this
                    .textLine("Is contract verified by explorer? : Yes")
                    .textLine("Contract name: " + token.getSecurity().getContractName())
                    .textLine("License type: " + token.getSecurity().getLicenseType())
                    .textLine(String.format("Compiler version: %s <b>(%s%s possible vulnerabilities)</b>", compilerVersion, Emoji.WARNING, amount));

                return this;
            }

            this
                .textLine("Is contract verified by explorer? : No");

            return this;
        }

        public String build() {
            return this.message.toString();
        }
    }
}
