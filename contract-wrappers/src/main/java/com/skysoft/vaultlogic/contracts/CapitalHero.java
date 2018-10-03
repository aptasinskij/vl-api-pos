package com.skysoft.vaultlogic.contracts;

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
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.contracts.SmartContractEvent;
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
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806115908339810180604052810190808051906020019092919050505080806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505061150b806100856000396000f3006080604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630cb4004d146100ca578063500260381461013157806352fd43a11461015e5780635b56a8fa146101955780635f3062cd146101c2578063636b5409146101ef5780636886a5c21461021c5780639fc78a9114610253578063a3bfdf4714610280578063abccc23f146102ad578063ae1f1ee114610366578063d7bbd1d414610393578063fc7055dd146103c0575b600080fd5b3480156100d657600080fd5b5061012f6004803603810190808035906020019092919080359060200190929190803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610401565b005b34801561013d57600080fd5b5061015c60048036038101908080359060200190929190505050610543565b005b34801561016a57600080fd5b506101936004803603810190808035906020019092919080359060200190929190505050610670565b005b3480156101a157600080fd5b506101c0600480360381019080803590602001909291905050506106b3565b005b3480156101ce57600080fd5b506101ed600480360381019080803590602001909291905050506108b2565b005b3480156101fb57600080fd5b5061021a6004803603810190808035906020019092919050505061099d565b005b34801561022857600080fd5b506102516004803603810190808035906020019092919080359060200190929190505050610b9c565b005b34801561025f57600080fd5b5061027e60048036038101908080359060200190929190505050610bdf565b005b34801561028c57600080fd5b506102ab60048036038101908080359060200190929190505050610e9e565b005b3480156102b957600080fd5b5061036460048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610f65565b005b34801561037257600080fd5b5061039160048036038101908080359060200190929190505050611247565b005b34801561039f57600080fd5b506103be60048036038101908080359060200190929190505050611289565b005b3480156103cc57600080fd5b506103ff6004803603810190808035906020019092919080359060200190929190803590602001909291905050506112cb565b005b61043f6040805190810160405280601281526020017f636173682d696e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff1663a97364058787878787876040518763ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808781526020018681526020018060200180602001838103835287878281815260200192506020028082843782019150508381038252858582818152602001925060200280828437820191505098505050505050505050602060405180830381600087803b1580156104ff57600080fd5b505af1158015610513573d6000803e3d6000fd5b505050506040513d602081101561052957600080fd5b810190808051906020019092919050505050505050505050565b60006105836040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff166350026038836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156105f157600080fd5b505af1158015610605573d6000803e3d6000fd5b505050506040513d602081101561061b57600080fd5b81019080805190602001909291905050509050801561066c577f0ad40631c90676315d189df642d606c501fa8fcfafa9deaa586576125c9acd57826040518082815260200191505060405180910390a15b5050565b7f280af031364d964d103568f906663e7e73cc09811a104da2ebfe8f65bdd185098282604051808381526020018281526020019250505060405180910390a15050565b600060606106f56040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff16635b56a8fa846040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561076357600080fd5b505af1158015610777573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060408110156107a157600080fd5b810190808051906020019092919080516401000000008111156107c357600080fd5b828101905060208101848111156107d957600080fd5b81518560018202830111640100000000821117156107f657600080fd5b50509291905050509150915081156108ad577f8710178e04e67e1032df473edb0aa71d941562b1e2e7ca6054384c0d9f84c05583826040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610871578082015181840152602081019050610856565b50505050905090810190601f16801561089e5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505050565b6108f06040805190810160405280601281526020017f636173682d696e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff1663690e7c09826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561095e57600080fd5b505af1158015610972573d6000803e3d6000fd5b505050506040513d602081101561098857600080fd5b81019080805190602001909291905050505050565b600060606109df6040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff1663636b5409846040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610a4d57600080fd5b505af1158015610a61573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506040811015610a8b57600080fd5b81019080805190602001909291908051640100000000811115610aad57600080fd5b82810190506020810184811115610ac357600080fd5b8151856001820283011164010000000082111715610ae057600080fd5b5050929190505050915091508115610b97577f8710178e04e67e1032df473edb0aa71d941562b1e2e7ca6054384c0d9f84c05583826040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610b5b578082015181840152602081019050610b40565b50505050905090810190601f168015610b885780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505050565b7fc5056889444309461095c5b36d568a7e37e4e0114c283a3f59810e6e7d5e1b838282604051808381526020018281526020019250505060405180910390a15050565b6000606080610c226040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff16639fc78a91856040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610c9057600080fd5b505af1158015610ca4573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506060811015610cce57600080fd5b81019080805190602001909291908051640100000000811115610cf057600080fd5b82810190506020810184811115610d0657600080fd5b8151856001820283011164010000000082111715610d2357600080fd5b50509291906020018051640100000000811115610d3f57600080fd5b82810190506020810184811115610d5557600080fd5b8151856001820283011164010000000082111715610d7257600080fd5b50509291905050509250925092508215610e98577ff9d12c94bc5aee6b5b70f5e27e34a1065cba2fd55e708a2603d0c6d050738f87848383604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b83811015610df4578082015181840152602081019050610dd9565b50505050905090810190601f168015610e215780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610e5a578082015181840152602081019050610e3f565b50505050905090810190601f168015610e875780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15b50505050565b610edc6040805190810160405280600f81526020017f73657373696f6e2d6d616e616765720000000000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff1663a3bfdf47826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610f4a57600080fd5b505af1158015610f5e573d6000803e3d6000fd5b5050505050565b6000610fa56040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c65720000000000000000000000000000815250611317565b73ffffffffffffffffffffffffffffffffffffffff1663abccc23f8585856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561103857808201518184015260208101905061101d565b50505050905090810190601f1680156110655780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561109e578082015181840152602081019050611083565b50505050905090810190601f1680156110cb5780820380516001836020036101000a031916815260200191505b5095505050505050602060405180830381600087803b1580156110ed57600080fd5b505af1158015611101573d6000803e3d6000fd5b505050506040513d602081101561111757600080fd5b810190808051906020019092919050505090508015611241577e2244f9b4aab5a13dd33698ab25337ce091fd3ba4ebffde5a0c283ae2ec7a9f848484604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561119d578082015181840152602081019050611182565b50505050905090810190601f1680156111ca5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156112035780820151818401526020810190506111e8565b50505050905090810190601f1680156112305780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15b50505050565b7fd2fe606d6e5a1d7a66766fa1df5b7868957cdeb3e929db537e6cea563a79e8e64282604051808381526020018281526020019250505060405180910390a150565b7f315accd74fc751a34409a65dfa01d8f9e8f1993869804f95ccdf4bc31a3fd8d94282604051808381526020018281526020019250505060405180910390a150565b7f935559313f338876d604cab515bcfd3d59ab91abd80afb5d5344c27ae379389f83838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156113c25780820151818401526020810190506113a7565b50505050905090810190601f1680156113ef5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561140e57600080fd5b505af1158015611422573d6000803e3d6000fd5b505050506040513d602081101561143857600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16141515156114da576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f52656769737472792072657475726e656420307830000000000000000000000081525060200191505060405180910390fd5b9190505600a165627a7a723058202d02412fd0067476c574b0d93c88627dfdfd17f3e7cbb9d05d3b883d521f8e250029";

    public static final String FUNC_OPENCASHINCHANNEL = "openCashInChannel";

    public static final String FUNC_CLOSECASHINCHANNEL = "closeCashInChannel";

    public static final String FUNC_CASHINCHANNELOPENED = "cashInChannelOpened";

    public static final String FUNC_CASHINBALANCEUPDATE = "cashInBalanceUpdate";

    public static final String FUNC_CASHINCHANNELCLOSED = "cashInChannelClosed";

    public static final String FUNC_NEWSESSIONCREATED = "newSessionCreated";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final String FUNC_SESSIONCLOSED = "sessionClosed";

    public static final String FUNC_SCANQRCODEWITHLIGHTS = "scanQRCodeWithLights";

    public static final String FUNC_SCANQRCODE = "scanQRCode";

    public static final String FUNC_STOPQRSCANNING = "stopQRScanning";

    public static final String FUNC_GETRECEIPTURL = "getReceiptUrl";

    public static final String FUNC_PRINTRECEIPT = "printReceipt";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SESSIONCLOSED_EVENT = new Event("SessionClosed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event QRCODESCANNED_EVENT = new Event("QRCodeScanned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event QRSCANNINGSTOPPED_EVENT = new Event("QRScanningStopped", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event RECEIPTURLRECEIVED_EVENT = new Event("ReceiptURLReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event RECEIPTPRINTED_EVENT = new Event("ReceiptPrinted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0xe60c1ea54fd0ef194d9c82b90cec295dd6c5249b");
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
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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

    public List<QRCodeScannedEventResponse> getQRCodeScannedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(QRCODESCANNED_EVENT, transactionReceipt);
        ArrayList<QRCodeScannedEventResponse> responses = new ArrayList<QRCodeScannedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QRCodeScannedEventResponse typedResponse = new QRCodeScannedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<QRCodeScannedEventResponse> qRCodeScannedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, QRCodeScannedEventResponse>() {
            @Override
            public QRCodeScannedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(QRCODESCANNED_EVENT, log);
                QRCodeScannedEventResponse typedResponse = new QRCodeScannedEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<QRCodeScannedEventResponse> qRCodeScannedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QRCODESCANNED_EVENT));
        return qRCodeScannedEventObservable(filter);
    }

    public List<QRScanningStoppedEventResponse> getQRScanningStoppedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(QRSCANNINGSTOPPED_EVENT, transactionReceipt);
        ArrayList<QRScanningStoppedEventResponse> responses = new ArrayList<QRScanningStoppedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QRScanningStoppedEventResponse typedResponse = new QRScanningStoppedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<QRScanningStoppedEventResponse> qRScanningStoppedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, QRScanningStoppedEventResponse>() {
            @Override
            public QRScanningStoppedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(QRSCANNINGSTOPPED_EVENT, log);
                QRScanningStoppedEventResponse typedResponse = new QRScanningStoppedEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<QRScanningStoppedEventResponse> qRScanningStoppedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QRSCANNINGSTOPPED_EVENT));
        return qRScanningStoppedEventObservable(filter);
    }

    public List<ReceiptURLReceivedEventResponse> getReceiptURLReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTURLRECEIVED_EVENT, transactionReceipt);
        ArrayList<ReceiptURLReceivedEventResponse> responses = new ArrayList<ReceiptURLReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptURLReceivedEventResponse typedResponse = new ReceiptURLReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.url = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptURLReceivedEventResponse> receiptURLReceivedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptURLReceivedEventResponse>() {
            @Override
            public ReceiptURLReceivedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTURLRECEIVED_EVENT, log);
                ReceiptURLReceivedEventResponse typedResponse = new ReceiptURLReceivedEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.url = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptURLReceivedEventResponse> receiptURLReceivedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTURLRECEIVED_EVENT));
        return receiptURLReceivedEventObservable(filter);
    }

    public List<ReceiptPrintedEventResponse> getReceiptPrintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTPRINTED_EVENT, transactionReceipt);
        ArrayList<ReceiptPrintedEventResponse> responses = new ArrayList<ReceiptPrintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptPrintedEventResponse typedResponse = new ReceiptPrintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptPrintedEventResponse> receiptPrintedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptPrintedEventResponse>() {
            @Override
            public ReceiptPrintedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTPRINTED_EVENT, log);
                ReceiptPrintedEventResponse typedResponse = new ReceiptPrintedEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.data = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptPrintedEventResponse> receiptPrintedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTPRINTED_EVENT));
        return receiptPrintedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> openCashInChannel(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_OPENCASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeCashInChannel(BigInteger sessionId, BigInteger channelId, List<BigInteger> _fees, List<String> _parties) {
        final Function function = new Function(
                FUNC_CLOSECASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(channelId), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_fees, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_parties, org.web3j.abi.datatypes.Address.class))), 
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

    public RemoteCall<TransactionReceipt> newSessionCreated(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_NEWSESSIONCREATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
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

    public RemoteCall<TransactionReceipt> scanQRCodeWithLights(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_SCANQRCODEWITHLIGHTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> scanQRCode(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_SCANQRCODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> stopQRScanning(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_STOPQRSCANNING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getReceiptUrl(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_GETRECEIPTURL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> printReceipt(BigInteger _sessionId, String _id, String _data) {
        final Function function = new Function(
                FUNC_PRINTRECEIPT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_id), 
                new org.web3j.abi.datatypes.Utf8String(_data)), 
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

    public interface CapitalHeroEvent extends SmartContractEvent {
    }

    public static class CashInOpenedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger sessionId;
    }

    public static class CashInClosedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger sessionId;
    }

    public static class CashInBalanceUpdatedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger channelId;

        public BigInteger balance;

        public BigInteger sessionId;
    }

    public static class SessionCreatedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger timestamp;

        public BigInteger sessionId;
    }

    public static class SessionClosedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger timestamp;

        public BigInteger sessionId;
    }

    public static class QRCodeScannedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger sessionId;

        public String url;
    }

    public static class QRScanningStoppedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger sessionId;
    }

    public static class ReceiptURLReceivedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger sessionId;

        public String id;

        public String url;
    }

    public static class ReceiptPrintedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger sessionId;

        public String id;

        public String data;
    }
}
