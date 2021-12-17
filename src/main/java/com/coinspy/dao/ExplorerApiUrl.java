package com.coinspy.dao;

/**
 * Create URL to achieve data from blockchain explorer based on solidity
 * Possibility to communicate with etherscan, polygonscan, bscscan
 *
 * @see <a href="https://etherscan.io/apis">https://etherscan.io/apis</a>
 * <a href="https://bscscan.com/apis">https://bscscan.com/apis</a>
 * <a href="https://polygonscan.com/apis">https://polygonscan.com/apis</a>
 */

public class ExplorerApiUrl {

    private String root;
    private String apiKey;
    private String module;
    private String action;
    private String fromBlock;
    private String toBlock;
    private String address;
    private String topic0;
    private String topic01;
    private String topic1;
    private String txHash;
    private String contractAddress;
    private String sort;
    private String page;
    private String offset;


    public String getFinalUrl(){
        return root +
                addParam(module, "&module=") +
                addParam(action, "&action=") +
                addParam(fromBlock, "&fromBlock=") +
                addParam(toBlock, "&toBlock=") +
                addParam(address, "&address=") +
                addParam(topic0, "&topic0=") +
                addParam(topic01, "&topic0_1=") +
                addParam(topic1, "&topic1=") +
                addParam(txHash, "&txHash=") +
                addParam(contractAddress, "&contractaddress=") +
                addParam(sort, "&sort=") +
                addParam(page, "&page=") +
                addParam(offset, "&offset=") +
                addParam(apiKey, "&apiKey=");
    }

    private String addParam(String value, String parameter){
        if(value != null && !value.isEmpty()){
            return parameter + value;
        }
        return "";
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setFromBlock(String fromBlock) {
        this.fromBlock = fromBlock;
    }

    public void setToBlock(String toBlock) {
        this.toBlock = toBlock;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTopic0(String topic0) {
        this.topic0 = topic0;
    }

    public void setTopic01(String topic01) {
        this.topic01 = topic01;
    }

    public void setTopic1(String topic1) {
        this.topic1 = topic1;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }


    public static final class ExplorerApiUrlBuilder {
        private String root;
        private String apiKey;
        private String module;
        private String action;
        private String fromBlock;
        private String toBlock;
        private String address;
        private String topic0;
        private String topic01;
        private String topic1;
        private String txHash;
        private String contractAddress;
        private String sort;
        private String page;
        private String offset;

        private ExplorerApiUrlBuilder() {
        }

        public static ExplorerApiUrlBuilder builder(String root) {
            var urlBuilder = new ExplorerApiUrlBuilder();
            urlBuilder.root = root;
            return urlBuilder;
        }

        public ExplorerApiUrlBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public ExplorerApiUrlBuilder module(String module) {
            this.module = module;
            return this;
        }

        public ExplorerApiUrlBuilder action(String action) {
            this.action = action;
            return this;
        }

        public ExplorerApiUrlBuilder fromBlock(String fromBlock) {
            this.fromBlock = fromBlock;
            return this;
        }

        public ExplorerApiUrlBuilder toBlock(String toBlock) {
            this.toBlock = toBlock;
            return this;
        }

        public ExplorerApiUrlBuilder address(String address) {
            this.address = address;
            return this;
        }

        public ExplorerApiUrlBuilder topic0(String topic0) {
            this.topic0 = topic0;
            return this;
        }

        public ExplorerApiUrlBuilder topic01(String topic01) {
            this.topic01 = topic01;
            return this;
        }

        public ExplorerApiUrlBuilder topic1(String topic1) {
            this.topic1 = topic1;
            return this;
        }

        public ExplorerApiUrlBuilder txHash(String txHash) {
            this.txHash = txHash;
            return this;
        }

        public ExplorerApiUrlBuilder contractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
            return this;
        }

        public ExplorerApiUrlBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public ExplorerApiUrlBuilder page(String page) {
            this.page = page;
            return this;
        }

        public ExplorerApiUrlBuilder offset(String offset) {
            this.offset = offset;
            return this;
        }

        public ExplorerApiUrl build() {
            var explorerApiUrl = new ExplorerApiUrl();
            explorerApiUrl.root = this.root;
            explorerApiUrl.module = this.module;
            explorerApiUrl.action = this.action;
            explorerApiUrl.toBlock = this.toBlock;
            explorerApiUrl.topic01 = this.topic01;
            explorerApiUrl.apiKey = this.apiKey;
            explorerApiUrl.topic0 = this.topic0;
            explorerApiUrl.address = this.address;
            explorerApiUrl.fromBlock = this.fromBlock;
            explorerApiUrl.topic1 = this.topic1;
            explorerApiUrl.txHash = this.txHash;
            explorerApiUrl.contractAddress = this.contractAddress;
            explorerApiUrl.sort = this.sort;
            explorerApiUrl.page = this.page;
            explorerApiUrl.offset = this.offset;
            return explorerApiUrl;
        }
    }
}
