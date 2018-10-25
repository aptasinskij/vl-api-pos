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
    private static final String BINARY = "0x608060405234801561001057600080fd5b5060405160208061094c833981016040525160008054600160a060020a03909216600160a060020a03199092169190911790556108fa806100526000396000f3006080604052600436106100c45763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630c0c628e81146100c957806313e70896146100e6578063599529e41461010157806383a37dee146101285780638bdd902f146101435780639c5c6b9a1461015e578063b23b31c014610173578063c2a9219214610188578063e9d116ab146101a0578063edcaeb24146101b5578063f391d78d146101ca578063f9df808a146101e5578063fd721c16146101fa575b600080fd5b3480156100d557600080fd5b506100e4600435602435610218565b005b3480156100f257600080fd5b506100e460043560243561050b565b34801561010d57600080fd5b50610116610759565b60408051918252519081900360200190f35b34801561013457600080fd5b506100e460043560243561075f565b34801561014f57600080fd5b506100e460043560243561079e565b34801561016a57600080fd5b506101166107e7565b34801561017f57600080fd5b506101166107ed565b34801561019457600080fd5b506100e46004356107f3565b3480156101ac57600080fd5b50610116610829565b3480156101c157600080fd5b5061011661082f565b3480156101d657600080fd5b506100e4600435602435610835565b3480156101f157600080fd5b50610116610874565b34801561020657600080fd5b506100e460043560243560443561087a565b6040805160008082526020808301828152825460808501865260128587019081527f636173682d696e2d636f6e74726f6c6c657200000000000000000000000000006060870190815296517f693ec85e000000000000000000000000000000000000000000000000000000008152600481019485528151602482015281519697939673ffffffffffffffffffffffffffffffffffffffff9093169463693ec85e94929390928392604401918083838b5b838110156102e05781810151838201526020016102c8565b50505050905090810190601f16801561030d5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561032c57600080fd5b505af1158015610340573d6000803e3d6000fd5b505050506040513d602081101561035657600080fd5b50516040517f352c414c00000000000000000000000000000000000000000000000000000000815260048101878152602482018790526383a37dee30640100000000810282811768010000000000000000908102608487015263f391d78d9182170260a486015260c060448601908152895160c4870152895173ffffffffffffffffffffffffffffffffffffffff9097169663352c414c968d968d968d968d9690959194869491939291606481019160e4909101906020808c01910280838360005b83811015610430578181015183820152602001610418565b50505050905001838103825288818151815260200191508051906020019060200280838360005b8381101561046f578181015183820152602001610457565b505050509050019a5050505050505050505050602060405180830381600087803b15801561049c57600080fd5b505af11580156104b0573d6000803e3d6000fd5b505050506040513d60208110156104c657600080fd5b505190508015610504576040805186815290517fc30e01dec4847b728523d59dfa3cc011df28c8c575f1a740df536f6de2cdcb829181900360200190a15b5050505050565b60008054604080518082018252601281527f636173682d696e2d636f6e74726f6c6c65720000000000000000000000000000602080830191825292517f693ec85e0000000000000000000000000000000000000000000000000000000081526004810193845282516024820152825173ffffffffffffffffffffffffffffffffffffffff9095169463693ec85e94928392604401918083838b5b838110156105bd5781810151838201526020016105a5565b50505050905090810190601f1680156105ea5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561060957600080fd5b505af115801561061d573d6000803e3d6000fd5b505050506040513d602081101561063357600080fd5b5051604080517fe5be8eec0000000000000000000000000000000000000000000000000000000081526004810186905260248101859052680100000000000000006401000000003002638bdd902f81178202604484015263fd721c1681178202606484015263c2a9219217026084820152905173ffffffffffffffffffffffffffffffffffffffff9092169163e5be8eec9160a4808201926020929091908290030181600087803b1580156106e757600080fd5b505af11580156106fb573d6000803e3d6000fd5b505050506040513d602081101561071157600080fd5b5051905080156107545760018390556040805184815290517f17d78b8713d172d99cd762a9783a1afcdd2fe1da07fb8680ff91bb611bc4044e9181900360200190a15b505050565b60045481565b604080518381526020810183905281517f56599de87eb9dacfc736f3644ece08f97fb3ee567173eeef964163990e6d6028929181900390910190a15050565b60028290556003819055604080518381526020810183905281517f046cd7c0f9475bdcc2bccb30738c935672ccd94d920724c9007a0b37be87eddf929181900390910190a15050565b60055481565b60065481565b6040805182815290517fb1e95e836b94de0679eabf7c724e2f17b4943d19c55c83042472490ea708794c9181900360200190a150565b60025481565b60015481565b604080518381526020810183905281517f3a560a5b11e09c59628b311f70e7bb3d743003b32d2e0f9ae38174a2c187d9ed929181900390910190a15050565b60035481565b600483905560058290556006819055604080518481526020810184905280820183905290517f6c0357152e8cad202681a829285fb50149a2ae043c139d8668872f2c45cd4a2b9181900360600190a15050505600a165627a7a72305820abfaaff62f80d829a886a7e7fcd15a0e9fd278c54c92ccd36105a420f3e20fdc0029";

    public static final String FUNC_LASTBALANCEUPDATESESSION = "lastBalanceUpdateSession";

    public static final String FUNC_LASTBALANCEUPDATECHANNEL = "lastBalanceUpdateChannel";

    public static final String FUNC_LASTBALANCEUPDATEAMOUNT = "lastBalanceUpdateAmount";

    public static final String FUNC_LASTSUCCESSOPENSESSION = "lastSuccessOpenSession";

    public static final String FUNC_LASTOPENACCEPTEDSESSION = "lastOpenAcceptedSession";

    public static final String FUNC_LASTSUCCESSOPENCHANNEL = "lastSuccessOpenChannel";

    public static final String FUNC_OPENCASHINCHANNEL = "openCashInChannel";

    public static final String FUNC_SUCCESSOPEN = "successOpen";

    public static final String FUNC_BALANCEUPDATE = "balanceUpdate";

    public static final String FUNC_FAILOPEN = "failOpen";

    public static final String FUNC_CLOSECASHINCHANNEL = "closeCashInChannel";

    public static final String FUNC_SUCCESSCLOSE = "successClose";

    public static final String FUNC_FAILCLOSE = "failClose";

    public static final Event OPENACCEPTED_EVENT = new Event("OpenAccepted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SUCCESSOPEN_EVENT = new Event("SuccessOpen", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BALANCEUPDATE_EVENT = new Event("BalanceUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FAILOPEN_EVENT = new Event("FailOpen", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSEACCEPTED_EVENT = new Event("CloseAccepted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event SUCCESSCLOSE_EVENT = new Event("SuccessClose", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FAILCLOSE_EVENT = new Event("FailClose", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("84107", "0x9a89723117ee349a48fac5b1d8f240a9d19c92c5");
        _addresses.put("37609", "0x981e67dfe7c73e82b67b18fd4ecdcfb210b5bc43");
        _addresses.put("5777", "0x73376604f41d124703381217c88ab6034f38bd45");
    }

    protected CapitalHero(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CapitalHero(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> lastBalanceUpdateSession() {
        final Function function = new Function(FUNC_LASTBALANCEUPDATESESSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastBalanceUpdateChannel() {
        final Function function = new Function(FUNC_LASTBALANCEUPDATECHANNEL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastBalanceUpdateAmount() {
        final Function function = new Function(FUNC_LASTBALANCEUPDATEAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastSuccessOpenSession() {
        final Function function = new Function(FUNC_LASTSUCCESSOPENSESSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastOpenAcceptedSession() {
        final Function function = new Function(FUNC_LASTOPENACCEPTEDSESSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> lastSuccessOpenChannel() {
        final Function function = new Function(FUNC_LASTSUCCESSOPENCHANNEL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _context) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_context)));
        return deployRemoteCall(CapitalHero.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CapitalHero> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _context) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_context)));
        return deployRemoteCall(CapitalHero.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<OpenAcceptedEventResponse> getOpenAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENACCEPTED_EVENT, transactionReceipt);
        ArrayList<OpenAcceptedEventResponse> responses = new ArrayList<OpenAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenAcceptedEventResponse typedResponse = new OpenAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenAcceptedEventResponse> openAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenAcceptedEventResponse>() {
            @Override
            public OpenAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENACCEPTED_EVENT, log);
                OpenAcceptedEventResponse typedResponse = new OpenAcceptedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenAcceptedEventResponse> openAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENACCEPTED_EVENT));
        return openAcceptedEventObservable(filter);
    }

    public List<SuccessOpenEventResponse> getSuccessOpenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SUCCESSOPEN_EVENT, transactionReceipt);
        ArrayList<SuccessOpenEventResponse> responses = new ArrayList<SuccessOpenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SuccessOpenEventResponse typedResponse = new SuccessOpenEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SuccessOpenEventResponse> successOpenEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SuccessOpenEventResponse>() {
            @Override
            public SuccessOpenEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SUCCESSOPEN_EVENT, log);
                SuccessOpenEventResponse typedResponse = new SuccessOpenEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SuccessOpenEventResponse> successOpenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SUCCESSOPEN_EVENT));
        return successOpenEventObservable(filter);
    }

    public List<BalanceUpdateEventResponse> getBalanceUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BALANCEUPDATE_EVENT, transactionReceipt);
        ArrayList<BalanceUpdateEventResponse> responses = new ArrayList<BalanceUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BalanceUpdateEventResponse typedResponse = new BalanceUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BalanceUpdateEventResponse> balanceUpdateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, BalanceUpdateEventResponse>() {
            @Override
            public BalanceUpdateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BALANCEUPDATE_EVENT, log);
                BalanceUpdateEventResponse typedResponse = new BalanceUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<BalanceUpdateEventResponse> balanceUpdateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BALANCEUPDATE_EVENT));
        return balanceUpdateEventObservable(filter);
    }

    public List<FailOpenEventResponse> getFailOpenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FAILOPEN_EVENT, transactionReceipt);
        ArrayList<FailOpenEventResponse> responses = new ArrayList<FailOpenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FailOpenEventResponse typedResponse = new FailOpenEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FailOpenEventResponse> failOpenEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, FailOpenEventResponse>() {
            @Override
            public FailOpenEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FAILOPEN_EVENT, log);
                FailOpenEventResponse typedResponse = new FailOpenEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FailOpenEventResponse> failOpenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FAILOPEN_EVENT));
        return failOpenEventObservable(filter);
    }

    public List<CloseAcceptedEventResponse> getCloseAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSEACCEPTED_EVENT, transactionReceipt);
        ArrayList<CloseAcceptedEventResponse> responses = new ArrayList<CloseAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseAcceptedEventResponse typedResponse = new CloseAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseAcceptedEventResponse> closeAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseAcceptedEventResponse>() {
            @Override
            public CloseAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSEACCEPTED_EVENT, log);
                CloseAcceptedEventResponse typedResponse = new CloseAcceptedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseAcceptedEventResponse> closeAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSEACCEPTED_EVENT));
        return closeAcceptedEventObservable(filter);
    }

    public List<SuccessCloseEventResponse> getSuccessCloseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SUCCESSCLOSE_EVENT, transactionReceipt);
        ArrayList<SuccessCloseEventResponse> responses = new ArrayList<SuccessCloseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SuccessCloseEventResponse typedResponse = new SuccessCloseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SuccessCloseEventResponse> successCloseEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SuccessCloseEventResponse>() {
            @Override
            public SuccessCloseEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SUCCESSCLOSE_EVENT, log);
                SuccessCloseEventResponse typedResponse = new SuccessCloseEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SuccessCloseEventResponse> successCloseEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SUCCESSCLOSE_EVENT));
        return successCloseEventObservable(filter);
    }

    public List<FailCloseEventResponse> getFailCloseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FAILCLOSE_EVENT, transactionReceipt);
        ArrayList<FailCloseEventResponse> responses = new ArrayList<FailCloseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FailCloseEventResponse typedResponse = new FailCloseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FailCloseEventResponse> failCloseEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, FailCloseEventResponse>() {
            @Override
            public FailCloseEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FAILCLOSE_EVENT, log);
                FailCloseEventResponse typedResponse = new FailCloseEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashInId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FailCloseEventResponse> failCloseEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FAILCLOSE_EVENT));
        return failCloseEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> openCashInChannel(BigInteger _sessionId, BigInteger _maxAmount) {
        final Function function = new Function(
                FUNC_OPENCASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_maxAmount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successOpen(BigInteger _sessionId, BigInteger _cashInId) {
        final Function function = new Function(
                FUNC_SUCCESSOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> balanceUpdate(BigInteger _sessionId, BigInteger _cashInId, BigInteger _amount) {
        final Function function = new Function(
                FUNC_BALANCEUPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failOpen(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_FAILOPEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeCashInChannel(BigInteger _sessionId, BigInteger _cashInId) {
        final Function function = new Function(
                FUNC_CLOSECASHINCHANNEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successClose(BigInteger _sessionId, BigInteger _cashInId) {
        final Function function = new Function(
                FUNC_SUCCESSCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failClose(BigInteger _sessionId, BigInteger _cashInId) {
        final Function function = new Function(
                FUNC_FAILCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashInId)), 
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

    public static class OpenAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }

    public static class SuccessOpenEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;
    }

    public static class BalanceUpdateEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;

        public BigInteger _amount;
    }

    public static class FailOpenEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }

    public static class CloseAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;
    }

    public static class SuccessCloseEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;
    }

    public static class FailCloseEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashInId;
    }
}
