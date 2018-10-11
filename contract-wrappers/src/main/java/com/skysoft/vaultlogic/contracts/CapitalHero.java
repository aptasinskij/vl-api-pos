package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
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
import org.web3j.tuples.generated.Tuple4;
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
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806119c48339810180604052810190808051906020019092919050505080806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505061193f806100856000396000f3006080604052600436106100d0576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630cb35bff146100d55780630cb4004d146102bf578063500260381461032657806352fd43a1146103535780635b56a8fa1461038a5780635f3062cd146103b7578063636b5409146103e45780636886a5c2146104115780639fc78a9114610448578063a3bfdf4714610475578063abccc23f146104a2578063ae1f1ee11461055b578063d7bbd1d414610588578063fc7055dd146105b5575b600080fd5b3480156100e157600080fd5b50610100600480360381019080803590602001909291905050506105f6565b6040518080602001806020018060200180602001858103855289818151815260200191508051906020019080838360005b8381101561014c578082015181840152602081019050610131565b50505050905090810190601f1680156101795780820380516001836020036101000a031916815260200191505b50858103845288818151815260200191508051906020019080838360005b838110156101b2578082015181840152602081019050610197565b50505050905090810190601f1680156101df5780820380516001836020036101000a031916815260200191505b50858103835287818151815260200191508051906020019080838360005b838110156102185780820151818401526020810190506101fd565b50505050905090810190601f1680156102455780820380516001836020036101000a031916815260200191505b50858103825286818151815260200191508051906020019080838360005b8381101561027e578082015181840152602081019050610263565b50505050905090810190601f1680156102ab5780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b3480156102cb57600080fd5b506103246004803603810190808035906020019092919080359060200190929190803590602001908201803590602001919091929391929390803590602001908201803590602001919091929391929390505050610835565b005b34801561033257600080fd5b5061035160048036038101908080359060200190929190505050610977565b005b34801561035f57600080fd5b506103886004803603810190808035906020019092919080359060200190929190505050610aa4565b005b34801561039657600080fd5b506103b560048036038101908080359060200190929190505050610ae7565b005b3480156103c357600080fd5b506103e260048036038101908080359060200190929190505050610ce6565b005b3480156103f057600080fd5b5061040f60048036038101908080359060200190929190505050610dd1565b005b34801561041d57600080fd5b506104466004803603810190808035906020019092919080359060200190929190505050610fd0565b005b34801561045457600080fd5b5061047360048036038101908080359060200190929190505050611013565b005b34801561048157600080fd5b506104a0600480360381019080803590602001909291905050506112d2565b005b3480156104ae57600080fd5b5061055960048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611399565b005b34801561056757600080fd5b506105866004803603810190808035906020019092919050505061167b565b005b34801561059457600080fd5b506105b3600480360381019080803590602001909291905050506116bd565b005b3480156105c157600080fd5b506105f46004803603810190808035906020019092919080359060200190929190803590602001909291905050506116ff565b005b60608060608061063a6040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff166339df5dcd866040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156106a857600080fd5b505af11580156106bc573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525060808110156106e657600080fd5b8101908080516401000000008111156106fe57600080fd5b8281019050602081018481111561071457600080fd5b815185600182028301116401000000008211171561073157600080fd5b5050929190602001805164010000000081111561074d57600080fd5b8281019050602081018481111561076357600080fd5b815185600182028301116401000000008211171561078057600080fd5b5050929190602001805164010000000081111561079c57600080fd5b828101905060208101848111156107b257600080fd5b81518560018202830111640100000000821117156107cf57600080fd5b505092919060200180516401000000008111156107eb57600080fd5b8281019050602081018481111561080157600080fd5b815185600182028301116401000000008211171561081e57600080fd5b505092919050505093509350935093509193509193565b6108736040805190810160405280601281526020017f636173682d696e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff1663a97364058787878787876040518763ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808781526020018681526020018060200180602001838103835287878281815260200192506020028082843782019150508381038252858582818152602001925060200280828437820191505098505050505050505050602060405180830381600087803b15801561093357600080fd5b505af1158015610947573d6000803e3d6000fd5b505050506040513d602081101561095d57600080fd5b810190808051906020019092919050505050505050505050565b60006109b76040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff166350026038836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610a2557600080fd5b505af1158015610a39573d6000803e3d6000fd5b505050506040513d6020811015610a4f57600080fd5b810190808051906020019092919050505090508015610aa0577f0ad40631c90676315d189df642d606c501fa8fcfafa9deaa586576125c9acd57826040518082815260200191505060405180910390a15b5050565b7f280af031364d964d103568f906663e7e73cc09811a104da2ebfe8f65bdd185098282604051808381526020018281526020019250505060405180910390a15050565b60006060610b296040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff16635b56a8fa846040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610b9757600080fd5b505af1158015610bab573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506040811015610bd557600080fd5b81019080805190602001909291908051640100000000811115610bf757600080fd5b82810190506020810184811115610c0d57600080fd5b8151856001820283011164010000000082111715610c2a57600080fd5b5050929190505050915091508115610ce1577f8710178e04e67e1032df473edb0aa71d941562b1e2e7ca6054384c0d9f84c05583826040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610ca5578082015181840152602081019050610c8a565b50505050905090810190601f168015610cd25780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505050565b610d246040805190810160405280601281526020017f636173682d696e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff1663690e7c09826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610d9257600080fd5b505af1158015610da6573d6000803e3d6000fd5b505050506040513d6020811015610dbc57600080fd5b81019080805190602001909291905050505050565b60006060610e136040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff1663636b5409846040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610e8157600080fd5b505af1158015610e95573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506040811015610ebf57600080fd5b81019080805190602001909291908051640100000000811115610ee157600080fd5b82810190506020810184811115610ef757600080fd5b8151856001820283011164010000000082111715610f1457600080fd5b5050929190505050915091508115610fcb577f8710178e04e67e1032df473edb0aa71d941562b1e2e7ca6054384c0d9f84c05583826040518083815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610f8f578082015181840152602081019050610f74565b50505050905090810190601f168015610fbc5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15b505050565b7fc5056889444309461095c5b36d568a7e37e4e0114c283a3f59810e6e7d5e1b838282604051808381526020018281526020019250505060405180910390a15050565b60006060806110566040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff16639fc78a91856040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156110c457600080fd5b505af11580156110d8573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250606081101561110257600080fd5b8101908080519060200190929190805164010000000081111561112457600080fd5b8281019050602081018481111561113a57600080fd5b815185600182028301116401000000008211171561115757600080fd5b5050929190602001805164010000000081111561117357600080fd5b8281019050602081018481111561118957600080fd5b81518560018202830111640100000000821117156111a657600080fd5b505092919050505092509250925082156112cc577ff9d12c94bc5aee6b5b70f5e27e34a1065cba2fd55e708a2603d0c6d050738f87848383604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561122857808201518184015260208101905061120d565b50505050905090810190601f1680156112555780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561128e578082015181840152602081019050611273565b50505050905090810190601f1680156112bb5780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15b50505050565b6113106040805190810160405280600f81526020017f73657373696f6e2d6d616e61676572000000000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff1663a3bfdf47826040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561137e57600080fd5b505af1158015611392573d6000803e3d6000fd5b5050505050565b60006113d96040805190810160405280601281526020017f73657373696f6e2d636f6e74726f6c6c6572000000000000000000000000000081525061174b565b73ffffffffffffffffffffffffffffffffffffffff1663abccc23f8585856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561146c578082015181840152602081019050611451565b50505050905090810190601f1680156114995780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156114d25780820151818401526020810190506114b7565b50505050905090810190601f1680156114ff5780820380516001836020036101000a031916815260200191505b5095505050505050602060405180830381600087803b15801561152157600080fd5b505af1158015611535573d6000803e3d6000fd5b505050506040513d602081101561154b57600080fd5b810190808051906020019092919050505090508015611675577e2244f9b4aab5a13dd33698ab25337ce091fd3ba4ebffde5a0c283ae2ec7a9f848484604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156115d15780820151818401526020810190506115b6565b50505050905090810190601f1680156115fe5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561163757808201518184015260208101905061161c565b50505050905090810190601f1680156116645780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15b50505050565b7fd2fe606d6e5a1d7a66766fa1df5b7868957cdeb3e929db537e6cea563a79e8e64282604051808381526020018281526020019250505060405180910390a150565b7f315accd74fc751a34409a65dfa01d8f9e8f1993869804f95ccdf4bc31a3fd8d94282604051808381526020018281526020019250505060405180910390a150565b7f935559313f338876d604cab515bcfd3d59ab91abd80afb5d5344c27ae379389f83838360405180848152602001838152602001828152602001935050505060405180910390a1505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156117f65780820151818401526020810190506117db565b50505050905090810190601f1680156118235780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561184257600080fd5b505af1158015611856573d6000803e3d6000fd5b505050506040513d602081101561186c57600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff161415151561190e576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f52656769737472792072657475726e656420307830000000000000000000000081525060200191505060405180910390fd5b9190505600a165627a7a72305820574651c8ddde394327838a267bffc86e8f2bbdb6e22c72933016807242d3d1c20029";

    public static final String FUNC_OPENCASHINCHANNEL = "openCashInChannel";

    public static final String FUNC_CLOSECASHINCHANNEL = "closeCashInChannel";

    public static final String FUNC_CASHINCHANNELOPENED = "cashInChannelOpened";

    public static final String FUNC_CASHINBALANCEUPDATE = "cashInBalanceUpdate";

    public static final String FUNC_CASHINCHANNELCLOSED = "cashInChannelClosed";

    public static final String FUNC_NEWSESSIONCREATED = "newSessionCreated";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final String FUNC_SESSIONCLOSED = "sessionClosed";

    public static final String FUNC_GETKIOSKINFO = "getKioskInfo";

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
        _addresses.put("77142", "0xb8652d2497ac16176f3b5c6e9e9f120c61a2756a");
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

    public RemoteCall<Tuple4<String, String, String, String>> getKioskInfo(BigInteger _sessionId) {
        final Function function = new Function(FUNC_GETKIOSKINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<String, String, String, String>>(
                new Callable<Tuple4<String, String, String, String>>() {
                    @Override
                    public Tuple4<String, String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
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
