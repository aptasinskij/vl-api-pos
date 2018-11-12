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
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806109f0833981016040525160008054600160a060020a03909216600160a060020a031990921691909117905561099e806100526000396000f30060806040526004361061008d5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166317bd8ac881146100925780633c3972ad1461016e5780634082de671461018657806355b0117a146101a15780637792c9bb146101ff578063b8e8d9f31461021a578063bed1348714610232578063e19866281461024a575b600080fd5b34801561009e57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261016c95833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506102689650505050505050565b005b34801561017a57600080fd5b5061016c6004356103dd565b34801561019257600080fd5b5061016c600435602435610413565b3480156101ad57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261016c95833595369560449491939091019190819084018382808284375094975061064c9650505050505050565b34801561020b57600080fd5b5061016c6004356024356106f1565b34801561022657600080fd5b5061016c600435610730565b34801561023e57600080fd5b5061016c6004356108f7565b34801561025657600080fd5b5061016c60043560243560443561092d565b7f5aa762a71fc9052fd096e25c33ec980a5d92a7ed64a106c1c0adc7b584ec67298484848460405180858152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b838110156102d85781810151838201526020016102c0565b50505050905090810190601f1680156103055780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b83811015610338578181015183820152602001610320565b50505050905090810190601f1680156103655780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b83811015610398578181015183820152602001610380565b50505050905090810190601f1680156103c55780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390a150505050565b6040805182815290517fd5a4f36edd330e00bf453f88f2b2303f2ea783b4e3c560613bcd2c8a5c01f1e29181900360200190a150565b60008054604080518082018252601281527f636173682d696e2d636f6e74726f6c6c65720000000000000000000000000000602080830191825292517f693ec85e0000000000000000000000000000000000000000000000000000000081526004810193845282516024820152825173ffffffffffffffffffffffffffffffffffffffff9095169563693ec85e959394938493604490930192918190849084905b838110156104cc5781810151838201526020016104b4565b50505050905090810190601f1680156104f95780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561051857600080fd5b505af115801561052c573d6000803e3d6000fd5b505050506040513d602081101561054257600080fd5b5051604080517fe5be8eec000000000000000000000000000000000000000000000000000000008152600481018590526024810184905268010000000000000000640100000000300263bed13487811782026044840152637792c9bb81178202606484015263e198662817026084820152905173ffffffffffffffffffffffffffffffffffffffff9092169163e5be8eec9160a48082019260009290919082900301818387803b1580156105f557600080fd5b505af1158015610609573d6000803e3d6000fd5b5050604080518581526020810185905281517f647cba28e04f550f0f4b65117e955dbbe9cce598f331c2a7ad84a19cc8706d879450908190039091019150a15050565b7ff4290e594ef3655b34ee7ff780b836ef65a92ad35737a1f15350dc9ccada25d182826040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156106b257818101518382015260200161069a565b50505050905090810190601f1680156106df5780820380516001836020036101000a031916815260200191505b50935050505060405180910390a15050565b604080518381526020810183905281517fc5056889444309461095c5b36d568a7e37e4e0114c283a3f59810e6e7d5e1b83929181900390910190a15050565b60008054604080517f693ec85e000000000000000000000000000000000000000000000000000000008152602060048201819052601160248301527f63616d6572612d636f6e74726f6c6c65720000000000000000000000000000006044830152915173ffffffffffffffffffffffffffffffffffffffff9093169363693ec85e93606480840194939192918390030190829087803b1580156107d257600080fd5b505af11580156107e6573d6000803e3d6000fd5b505050506040513d60208110156107fc57600080fd5b5051604080517f41ad3c6f000000000000000000000000000000000000000000000000000000008152600481018490526801000000000000000064010000000030026317bd8ac88117820260248401526355b0117a811782026044840152633c3972ad17026064820152905173ffffffffffffffffffffffffffffffffffffffff909216916341ad3c6f9160848082019260009290919082900301818387803b1580156108a857600080fd5b505af11580156108bc573d6000803e3d6000fd5b50506040805184815290517f47df8815d9cc5f0c5865fe0f72a2d229fd7006c3dd73f45d67793866399623b89350908190036020019150a150565b6040805182815290517ffa8aefc80899a624d8ea76e5b5ee8c91ca4bf59d5abab5b8462d52aa4bb057ec9181900360200190a150565b604080518481526020810184905280820183905290517fe78061f1ecca254f11af5ba62050f89f89ab45a16cf807ce56e3cd61a8b9d4e39181900360600190a15050505600a165627a7a7230582048d7cbe408c9576dd9b47a82b88f1af3ec87d898c2a7eeb4d2b29addcd49c9480029";

    public static final String FUNC_OPEN = "open";

    public static final String FUNC__OPENFAIL = "_openFail";

    public static final String FUNC__OPENSUCCESS = "_openSuccess";

    public static final String FUNC__BALANCEUPDATE = "_balanceUpdate";

    public static final String FUNC_SCANQRWITHLIGHTS = "scanQRWithLights";

    public static final String FUNC___SUCCESSSTARTSCAN = "__successStartScan";

    public static final String FUNC___QRSCANNED = "__QRScanned";

    public static final String FUNC___QRFAIL = "__QRFail";

    public static final Event REQUESTSENDED_EVENT = new Event("RequestSended", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINOPENED_EVENT = new Event("CashInOpened", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINUPDATE_EVENT = new Event("CashInUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CASHINFAIL_EVENT = new Event("CashInFail", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SCANACCEPTED_EVENT = new Event("ScanAccepted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SUCCESSQR_EVENT = new Event("SuccessQR", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event QRSCANNED_EVENT = new Event("QRScanned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event QRFAIL_EVENT = new Event("QRFail", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0x5c6f0a99ff5b834ca50eed605d4a2536d8157cf1");
    }

    protected CapitalHero(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CapitalHero(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _context) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_context)));
        return deployRemoteCall(CapitalHero.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _context) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_context)));
        return deployRemoteCall(CapitalHero.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<RequestSendedEventResponse> getRequestSendedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REQUESTSENDED_EVENT, transactionReceipt);
        ArrayList<RequestSendedEventResponse> responses = new ArrayList<RequestSendedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RequestSendedEventResponse typedResponse = new RequestSendedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._maxBalance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RequestSendedEventResponse> requestSendedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RequestSendedEventResponse>() {
            @Override
            public RequestSendedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REQUESTSENDED_EVENT, log);
                RequestSendedEventResponse typedResponse = new RequestSendedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._maxBalance = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RequestSendedEventResponse> requestSendedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REQUESTSENDED_EVENT));
        return requestSendedEventObservable(filter);
    }

    public List<CashInOpenedEventResponse> getCashInOpenedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINOPENED_EVENT, transactionReceipt);
        ArrayList<CashInOpenedEventResponse> responses = new ArrayList<CashInOpenedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInOpenedEventResponse typedResponse = new CashInOpenedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInOpenedEventResponse> cashInOpenedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINOPENED_EVENT));
        return cashInOpenedEventObservable(filter);
    }

    public List<CashInUpdateEventResponse> getCashInUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINUPDATE_EVENT, transactionReceipt);
        ArrayList<CashInUpdateEventResponse> responses = new ArrayList<CashInUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInUpdateEventResponse typedResponse = new CashInUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInUpdateEventResponse> cashInUpdateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInUpdateEventResponse>() {
            @Override
            public CashInUpdateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINUPDATE_EVENT, log);
                CashInUpdateEventResponse typedResponse = new CashInUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInUpdateEventResponse> cashInUpdateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINUPDATE_EVENT));
        return cashInUpdateEventObservable(filter);
    }

    public List<CashInFailEventResponse> getCashInFailEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CASHINFAIL_EVENT, transactionReceipt);
        ArrayList<CashInFailEventResponse> responses = new ArrayList<CashInFailEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CashInFailEventResponse typedResponse = new CashInFailEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CashInFailEventResponse> cashInFailEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CashInFailEventResponse>() {
            @Override
            public CashInFailEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CASHINFAIL_EVENT, log);
                CashInFailEventResponse typedResponse = new CashInFailEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CashInFailEventResponse> cashInFailEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CASHINFAIL_EVENT));
        return cashInFailEventObservable(filter);
    }

    public List<ScanAcceptedEventResponse> getScanAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SCANACCEPTED_EVENT, transactionReceipt);
        ArrayList<ScanAcceptedEventResponse> responses = new ArrayList<ScanAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ScanAcceptedEventResponse typedResponse = new ScanAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ScanAcceptedEventResponse> scanAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ScanAcceptedEventResponse>() {
            @Override
            public ScanAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SCANACCEPTED_EVENT, log);
                ScanAcceptedEventResponse typedResponse = new ScanAcceptedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ScanAcceptedEventResponse> scanAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SCANACCEPTED_EVENT));
        return scanAcceptedEventObservable(filter);
    }

    public List<SuccessQREventResponse> getSuccessQREvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SUCCESSQR_EVENT, transactionReceipt);
        ArrayList<SuccessQREventResponse> responses = new ArrayList<SuccessQREventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SuccessQREventResponse typedResponse = new SuccessQREventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._port = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._url = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._href = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SuccessQREventResponse> successQREventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SuccessQREventResponse>() {
            @Override
            public SuccessQREventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SUCCESSQR_EVENT, log);
                SuccessQREventResponse typedResponse = new SuccessQREventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._port = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._url = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._href = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SuccessQREventResponse> successQREventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SUCCESSQR_EVENT));
        return successQREventObservable(filter);
    }

    public List<QRScannedEventResponse> getQRScannedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(QRSCANNED_EVENT, transactionReceipt);
        ArrayList<QRScannedEventResponse> responses = new ArrayList<QRScannedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QRScannedEventResponse typedResponse = new QRScannedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._data = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<QRScannedEventResponse> qRScannedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, QRScannedEventResponse>() {
            @Override
            public QRScannedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(QRSCANNED_EVENT, log);
                QRScannedEventResponse typedResponse = new QRScannedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._data = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<QRScannedEventResponse> qRScannedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QRSCANNED_EVENT));
        return qRScannedEventObservable(filter);
    }

    public List<QRFailEventResponse> getQRFailEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(QRFAIL_EVENT, transactionReceipt);
        ArrayList<QRFailEventResponse> responses = new ArrayList<QRFailEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            QRFailEventResponse typedResponse = new QRFailEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<QRFailEventResponse> qRFailEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, QRFailEventResponse>() {
            @Override
            public QRFailEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(QRFAIL_EVENT, log);
                QRFailEventResponse typedResponse = new QRFailEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<QRFailEventResponse> qRFailEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(QRFAIL_EVENT));
        return qRFailEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> open(BigInteger _sessionId, BigInteger _maxBalance) {
        final Function function = new Function(
                FUNC_OPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_maxBalance)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> _openFail(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC__OPENFAIL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> _openSuccess(BigInteger _sessionId, BigInteger _cashInId) {
        final Function function = new Function(
                FUNC__OPENSUCCESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> _balanceUpdate(BigInteger _sessionId, BigInteger _cashInId, BigInteger _amount) {
        final Function function = new Function(
                FUNC__BALANCEUPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> scanQRWithLights(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_SCANQRWITHLIGHTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __successStartScan(BigInteger _sessionId, String _port, String _url, String _href) {
        final Function function = new Function(
                FUNC___SUCCESSSTARTSCAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_port), 
                new org.web3j.abi.datatypes.Utf8String(_url), 
                new org.web3j.abi.datatypes.Utf8String(_href)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __QRScanned(BigInteger _sessionId, String _data) {
        final Function function = new Function(
                FUNC___QRSCANNED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __QRFail(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC___QRFAIL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
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

    public static class RequestSendedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _maxBalance;
    }

    public static class CashInOpenedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;
    }

    public static class CashInUpdateEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;

        public BigInteger _amount;
    }

    public static class CashInFailEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }

    public static class ScanAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }

    public static class SuccessQREventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public String _port;

        public String _url;

        public String _href;
    }

    public static class QRScannedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public String _data;
    }

    public static class QRFailEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }
}
