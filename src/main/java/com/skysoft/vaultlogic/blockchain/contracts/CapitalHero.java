package com.skysoft.vaultlogic.blockchain.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class CapitalHero extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b5060405160208061085f8339810180604052810190808051906020019092919050505080806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050506107da806100856000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630c0c628e1461009357806352fd43a1146100ca5780635f3062cd146101015780636886a5c21461012e5780636a330d1d14610165578063a3bfdf471461017c578063ae1f1ee1146101a9578063fc7055dd146101d6575b600080fd5b34801561009f57600080fd5b506100c86004803603810190808035906020019092919080359060200190929190505050610217565b005b3480156100d657600080fd5b506100ff60048036038101908080359060200190929190803590602001909291905050506102e7565b005b34801561010d57600080fd5b5061012c6004803603810190808035906020019092919050505061032a565b005b34801561013a57600080fd5b506101636004803603810190808035906020019092919080359060200190929190505050610415565b005b34801561017157600080fd5b5061017a610458565b005b34801561018857600080fd5b506101a760048036038101908080359060200190929190505050610491565b005b3480156101b557600080fd5b506101d460048036038101908080359060200190929190505050610558565b005b3480156101e257600080fd5b5061021560048036038101908080359060200190929190803590602001909291908035906020019092919050505061059a565b005b6102556040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e6167657200000000000000000000008152506105e6565b73ffffffffffffffffffffffffffffffffffffffff16630c0c628e83836040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b1580156102cb57600080fd5b505af11580156102df573d6000803e3d6000fd5b505050505050565b7f280af031364d964d103568f906663e7e73cc09811a104da2ebfe8f65bdd185098282604051808381526020018281526020019250505060405180910390a15050565b6103686040805190810160405280601581526020017f636173682d6368616e6e656c732d6d616e6167657200000000000000000000008152506105e6565b73ffffffffffffffffffffffffffffffffffffffff16635f3062cd826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156103d657600080fd5b505af11580156103ea573d6000803e3d6000fd5b505050506040513d602081101561040057600080fd5b81019080805190602001909291905050505050565b7fc5056889444309461095c5b36d568a7e37e4e0114c283a3f59810e6e7d5e1b838282604051808381526020018281526020019250505060405180910390a15050565b7fba5b9081592c446fc890c90b94eb1cb2e823c52d85adafb0a116860c7549705a426040518082815260200191505060405180910390a1565b6104cf6040805190810160405280600f81526020017f73657373696f6e2d6d616e6167657200000000000000000000000000000000008152506105e6565b73ffffffffffffffffffffffffffffffffffffffff1663a3bfdf47826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561053d57600080fd5b505af1158015610551573d6000803e3d6000fd5b5050505050565b7fd2fe606d6e5a1d7a66766fa1df5b7868957cdeb3e929db537e6cea563a79e8e64282604051808381526020018281526020019250505060405180910390a150565b7f935559313f338876d604cab515bcfd3d59ab91abd80afb5d5344c27ae379389f83838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015610691578082015181840152602081019050610676565b50505050905090810190601f1680156106be5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156106dd57600080fd5b505af11580156106f1573d6000803e3d6000fd5b505050506040513d602081101561070757600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16141515156107a9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f52656769737472792072657475726e656420307830000000000000000000000081525060200191505060405180910390fd5b9190505600a165627a7a72305820e0cb189b0000be3f8468a75920a68a2e825b29fca67f1e13b18512d6d5e69ca70029";

    public static final String FUNC_OPENCASHINCHANNEL = "openCashInChannel";

    public static final String FUNC_CLOSECASHINCHANNEL = "closeCashInChannel";

    public static final String FUNC_CASHINCHANNELOPENED = "cashInChannelOpened";

    public static final String FUNC_CASHINBALANCEUPDATE = "cashInBalanceUpdate";

    public static final String FUNC_CASHINCHANNELCLOSED = "cashInChannelClosed";

    public static final String FUNC_NEWSESSIONCREATED = "newSessionCreated";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final String FUNC_SESSIONCLOSED = "sessionClosed";

    public static final Event CASHINOPENED_EVENT = new Event("CashInOpened", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINCLOSED_EVENT = new Event("CashInClosed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINBALANCEUPDATED_EVENT = new Event("CashInBalanceUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SESSIONCREATED_EVENT = new Event("SessionCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SESSIONCLOSED_EVENT = new Event("SessionClosed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x04d3d07229557903459acadd33f5e505f928bd3c");
        _addresses.put("4447", "0xb33d8395557533c57a265c975554ecf165c42ca1");
    }

    protected CapitalHero(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CapitalHero(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(CapitalHero.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(CapitalHero.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<CashInOpenedEventResponse> getCashInOpenedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINOPENED_EVENT, transactionReceipt);
        ArrayList<CashInOpenedEventResponse> responses = new ArrayList<CashInOpenedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInOpenedEventResponse typedResponse = new CashInOpenedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInOpenedEventResponse> cashInOpenedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInOpenedEventResponse>() {
            @Override
            public CashInOpenedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINOPENED_EVENT, log);
                CashInOpenedEventResponse typedResponse = new CashInOpenedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInOpenedEventResponse> cashInOpenedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINOPENED_EVENT));
        return cashInOpenedEventObservable(filter);
    }

    public List<CashInClosedEventResponse> getCashInClosedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINCLOSED_EVENT, transactionReceipt);
        ArrayList<CashInClosedEventResponse> responses = new ArrayList<CashInClosedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInClosedEventResponse typedResponse = new CashInClosedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInClosedEventResponse> cashInClosedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInClosedEventResponse>() {
            @Override
            public CashInClosedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINCLOSED_EVENT, log);
                CashInClosedEventResponse typedResponse = new CashInClosedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInClosedEventResponse> cashInClosedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINCLOSED_EVENT));
        return cashInClosedEventObservable(filter);
    }

    public List<CashInBalanceUpdatedEventResponse> getCashInBalanceUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINBALANCEUPDATED_EVENT, transactionReceipt);
        ArrayList<CashInBalanceUpdatedEventResponse> responses = new ArrayList<CashInBalanceUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInBalanceUpdatedEventResponse typedResponse = new CashInBalanceUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInBalanceUpdatedEventResponse> cashInBalanceUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInBalanceUpdatedEventResponse>() {
            @Override
            public CashInBalanceUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINBALANCEUPDATED_EVENT, log);
                CashInBalanceUpdatedEventResponse typedResponse = new CashInBalanceUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.channelId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.balance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInBalanceUpdatedEventResponse> cashInBalanceUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINBALANCEUPDATED_EVENT));
        return cashInBalanceUpdatedEventObservable(filter);
    }

    public List<SessionCreatedEventResponse> getSessionCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SESSIONCREATED_EVENT, transactionReceipt);
        ArrayList<SessionCreatedEventResponse> responses = new ArrayList<SessionCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SessionCreatedEventResponse typedResponse = new SessionCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SessionCreatedEventResponse> sessionCreatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SessionCreatedEventResponse>() {
            @Override
            public SessionCreatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SESSIONCREATED_EVENT, log);
                SessionCreatedEventResponse typedResponse = new SessionCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SessionCreatedEventResponse> sessionCreatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SESSIONCREATED_EVENT));
        return sessionCreatedEventObservable(filter);
    }

    public List<SessionClosedEventResponse> getSessionClosedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SESSIONCLOSED_EVENT, transactionReceipt);
        ArrayList<SessionClosedEventResponse> responses = new ArrayList<SessionClosedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SessionClosedEventResponse typedResponse = new SessionClosedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SessionClosedEventResponse> sessionClosedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SessionClosedEventResponse>() {
            @Override
            public SessionClosedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SESSIONCLOSED_EVENT, log);
                SessionClosedEventResponse typedResponse = new SessionClosedEventResponse();
                typedResponse.log = log;
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SessionClosedEventResponse> sessionClosedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SESSIONCLOSED_EVENT));
        return sessionClosedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> openCashInChannel(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_OPENCASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeCashInChannel(BigInteger sessionId, BigInteger channelId) {
        final Function function = new Function(
                FUNC_CLOSECASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(channelId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cashInChannelOpened(BigInteger channelId, BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CASHINCHANNELOPENED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cashInBalanceUpdate(BigInteger channelId, BigInteger balance, BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CASHINBALANCEUPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(balance), 
                new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cashInChannelClosed(BigInteger channelId, BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CASHINCHANNELCLOSED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> newSessionCreated() {
        final Function function = new Function(
                FUNC_NEWSESSIONCREATED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sessionClosed(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_SESSIONCLOSED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static CapitalHero load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CapitalHero(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CapitalHero load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CapitalHero(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class CashInOpenedEventResponse {
        public Log log;

        public BigInteger channelId;

        public BigInteger sessionId;
    }

    public static class CashInClosedEventResponse {
        public Log log;

        public BigInteger channelId;

        public BigInteger sessionId;
    }

    public static class CashInBalanceUpdatedEventResponse {
        public Log log;

        public BigInteger channelId;

        public BigInteger balance;

        public BigInteger sessionId;
    }

    public static class SessionCreatedEventResponse {
        public Log log;

        public BigInteger timestamp;
    }

    public static class SessionClosedEventResponse {
        public Log log;

        public BigInteger timestamp;

        public BigInteger sessionId;
    }
}
