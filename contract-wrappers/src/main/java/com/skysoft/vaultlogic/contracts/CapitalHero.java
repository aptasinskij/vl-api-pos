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
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806110ff833981016040525160008054600160a060020a03909216600160a060020a03199092169190911790556110ad806100526000396000f3006080604052600436106100b95763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166321c22cbf81146100be57806328161702146101d0578063497343c1146101eb5780636b26412c146102035780636c1a42d11461021e578063892b6bcb14610239578063968a329f146102d95780639b0e292d146102f1578063a7fed0a714610388578063b91df460146103a3578063c25f1cef146103bb578063ee3f5b51146103d6575b600080fd5b3480156100ca57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ce94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020808901358a01803580830284810184018652818552999c8b359c909b909a950198509296508101945090925082919085019084908082843750506040805187358901803560208181028481018201909552818452989b9a9989019892975090820195509350839250850190849080828437509497506103f19650505050505050565b005b3480156101dc57600080fd5b506101ce600435602435610762565b3480156101f757600080fd5b506101ce6004356107a1565b34801561020f57600080fd5b506101ce600435602435610970565b34801561022a57600080fd5b506101ce6004356024356109af565b34801561024557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ce94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750508435955050506020909201359150610b869050565b3480156102e557600080fd5b506101ce600435610c9c565b3480156102fd57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526101ce94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610cd29650505050505050565b34801561039457600080fd5b506101ce600435602435610dd6565b3480156103af57600080fd5b506101ce600435610fad565b3480156103c757600080fd5b506101ce600435602435610fe3565b3480156103e257600080fd5b506101ce600435602435611022565b60008054604080518082018252601381526000805160206110628339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169563693ec85e959394938493604490930192918190849084905b8381101561047557818101518382015260200161045d565b50505050905090810190601f1680156104a25780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156104c157600080fd5b505af11580156104d5573d6000803e3d6000fd5b505050506040513d60208110156104eb57600080fd5b8101908080519060200190929190505050600160a060020a031663472536b2868686868630600160a060020a0316639b0e292d30600160a060020a031663892b6bcb6040518a63ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001806020018a81526020018060200180602001898963ffffffff1690640100000000021768010000000000000000028152602001878763ffffffff169064010000000002176801000000000000000002815260200185810385528e818151815260200191508051906020019080838360005b838110156105e95781810151838201526020016105d1565b50505050905090810190601f1680156106165780820380516001836020036101000a031916815260200191505b5085810384528d5181528d516020918201918f019080838360005b83811015610649578181015183820152602001610631565b50505050905090810190601f1680156106765780820380516001836020036101000a031916815260200191505b5085810383528b5181528b51602091820191808e01910280838360005b838110156106ab578181015183820152602001610693565b5050505090500185810382528a818151815260200191508051906020019060200280838360005b838110156106ea5781810151838201526020016106d2565b505050509050019d5050505050505050505050505050600060405180830381600087803b15801561071a57600080fd5b505af115801561072e573d6000803e3d6000fd5b50506040517f24a400b68736c8b428a71a5fd0ee4286f47636c4b7aed7e795f9ac0901c9ce17925060009150a15050505050565b604080518381526020810183905281517f40ad38faf799d92da3321afd5a9d1083af4d20f1629b6e62340fcb0957188006929181900390910190a15050565b60008054604080518082018252601381526000805160206110628339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169563693ec85e959394938493604490930192918190849084905b8381101561082557818101518382015260200161080d565b50505050905090810190601f1680156108525780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561087157600080fd5b505af1158015610885573d6000803e3d6000fd5b505050506040513d602081101561089b57600080fd5b5051604080517f819432740000000000000000000000000000000000000000000000000000000081526004810184905268010000000000000000640100000000300263b91df46081178202602484015263968a329f170260448201529051600160a060020a039092169163819432749160648082019260009290919082900301818387803b15801561092c57600080fd5b505af1158015610940573d6000803e3d6000fd5b50506040517fd7215d0d4ca48b3d86b40bd63d01a5526d51b47906b1eeab836e370ee25c29ec925060009150a150565b604080518381526020810183905281517f76521613b06b893d0b9a0b3429e7be48c9b6e7d90e7b8e76256a827eb6e2966a929181900390910190a15050565b60008054604080518082018252601381526000805160206110628339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169563693ec85e959394938493604490930192918190849084905b83811015610a33578181015183820152602001610a1b565b50505050905090810190601f168015610a605780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610a7f57600080fd5b505af1158015610a93573d6000803e3d6000fd5b505050506040513d6020811015610aa957600080fd5b5051604080517f5caae8ff0000000000000000000000000000000000000000000000000000000081526004810185905260248101849052680100000000000000006401000000003002636b26412c8117820260448401526328161702170260648201529051600160a060020a0390921691635caae8ff9160848082019260009290919082900301818387803b158015610b4157600080fd5b505af1158015610b55573d6000803e3d6000fd5b50506040517f2483a4452e0fa8b936893537883f8468c693710c88d794b2ec80fe230f77419e925060009150a15050565b7f72246bf964abd136b99fef8286eba99780e3a2ac47c139f56b60fbc49f8cf96384848484604051808060200180602001858152602001848152602001838103835287818151815260200191508051906020019080838360005b83811015610bf8578181015183820152602001610be0565b50505050905090810190601f168015610c255780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b83811015610c58578181015183820152602001610c40565b50505050905090810190601f168015610c855780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a150505050565b6040805182815290517ff289636d9120474a5a40eaba226e0f9b59c21c684329879c9712a3510e34e0419181900360200190a150565b7f1bbdf9354448d31bf733a45dbf641a731a6178a54b241f73c4dda5118407de658282604051808060200180602001838103835285818151815260200191508051906020019080838360005b83811015610d36578181015183820152602001610d1e565b50505050905090810190601f168015610d635780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b83811015610d96578181015183820152602001610d7e565b50505050905090810190601f168015610dc35780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a15050565b60008054604080518082018252601381526000805160206110628339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169563693ec85e959394938493604490930192918190849084905b83811015610e5a578181015183820152602001610e42565b50505050905090810190601f168015610e875780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610ea657600080fd5b505af1158015610eba573d6000803e3d6000fd5b505050506040513d6020811015610ed057600080fd5b5051604080517f17eea922000000000000000000000000000000000000000000000000000000008152600481018590526024810184905268010000000000000000640100000000300263c25f1cef81178202604484015263ee3f5b51170260648201529051600160a060020a03909216916317eea9229160848082019260009290919082900301818387803b158015610f6857600080fd5b505af1158015610f7c573d6000803e3d6000fd5b50506040517f5ede4b87e8ee3e8e910ec11aa49f420e35e8fdc83e7ea390c48aea0cc2bc2203925060009150a15050565b6040805182815290517f385f6d093252552f44f20819c2c4370ed7f944bd81a74925f1729f5508abd88c9181900360200190a150565b604080518381526020810183905281517f2d715a98b70bbdc731f6bcca7bcb8596a34fc232688bafe63446d5eb912c9398929181900390910190a15050565b604080518381526020810183905281517f13c295bdcd4e7d1039f96ec3ac272ef5f0f046858bccdbdca919a0e519630a5f929181900390910190a150505600636173682d6f75742d636f6e74726f6c6c657200000000000000000000000000a165627a7a72305820c7cb36a3756e8e9046e6c19705b5813e13e6e5ce330ec2cca141491848e86f670029";

    public static final String FUNC_OPENCASHOUT = "openCashOut";

    public static final String FUNC___SUCCESSOPENCASHOUT = "__successOpenCashOut";

    public static final String FUNC___FAILOPENCASHOUT = "__failOpenCashOut";

    public static final String FUNC_VALIDATECASHOUT = "validateCashOut";

    public static final String FUNC___SUCCESSVALIDATECASHOUT = "__successValidateCashOut";

    public static final String FUNC___FAILVALIDATECASHOUT = "__failValidateCashOut";

    public static final String FUNC_CLOSECASHOUT = "closeCashOut";

    public static final String FUNC___SUCCESSCLOSECASHOUT = "__successCloseCashOut";

    public static final String FUNC___FAILCLOSECASHOUT = "__failCloseCashOut";

    public static final String FUNC_ROLLBACKCASHOUT = "rollbackCashOut";

    public static final String FUNC___SUCCESSROLLBACKCASHOUT = "__successRollbackCashOut";

    public static final String FUNC___FAILROLLBACKCASHOUT = "__failRollbackCashOut";

    public static final Event OPENCASHOUTACCEPTED_EVENT = new Event("OpenCashOutAccepted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event OPENCASHOUTSUCCESS_EVENT = new Event("OpenCashOutSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPENCASHOUTFAILED_EVENT = new Event("OpenCashOutFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event VALIDATECASHOUTACCEPTED_EVENT = new Event("ValidateCashOutAccepted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event VALIDATECASHOUTSUCCESS_EVENT = new Event("ValidateCashOutSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VALIDATECASHOUTFAILED_EVENT = new Event("ValidateCashOutFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSECASHOUTACCEPTED_EVENT = new Event("CloseCashOutAccepted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event CLOSECASHOUTSUCCESS_EVENT = new Event("CloseCashOutSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CLOSECASHOUTFAILED_EVENT = new Event("CloseCashOutFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ROLLBACKCASHOUTACCEPTED_EVENT = new Event("RollbackCashOutAccepted", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event ROLLBACKCASHOUTSUCCESS_EVENT = new Event("RollbackCashOutSuccess", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ROLLBACKCASHOUTFAILED_EVENT = new Event("RollbackCashOutFailed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("4447", "0x8e4c131b37383e431b9cd0635d3cf9f3f628edae");
        _addresses.put("89354", "0x910dd33c8637634911d63af02e35322dfcf5710b");
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

    public List<OpenCashOutAcceptedEventResponse> getOpenCashOutAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHOUTACCEPTED_EVENT, transactionReceipt);
        ArrayList<OpenCashOutAcceptedEventResponse> responses = new ArrayList<OpenCashOutAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashOutAcceptedEventResponse typedResponse = new OpenCashOutAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashOutAcceptedEventResponse> openCashOutAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashOutAcceptedEventResponse>() {
            @Override
            public OpenCashOutAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHOUTACCEPTED_EVENT, log);
                OpenCashOutAcceptedEventResponse typedResponse = new OpenCashOutAcceptedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashOutAcceptedEventResponse> openCashOutAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHOUTACCEPTED_EVENT));
        return openCashOutAcceptedEventObservable(filter);
    }

    public List<OpenCashOutSuccessEventResponse> getOpenCashOutSuccessEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHOUTSUCCESS_EVENT, transactionReceipt);
        ArrayList<OpenCashOutSuccessEventResponse> responses = new ArrayList<OpenCashOutSuccessEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashOutSuccessEventResponse typedResponse = new OpenCashOutSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._requestId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._fee = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashOutSuccessEventResponse> openCashOutSuccessEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashOutSuccessEventResponse>() {
            @Override
            public OpenCashOutSuccessEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHOUTSUCCESS_EVENT, log);
                OpenCashOutSuccessEventResponse typedResponse = new OpenCashOutSuccessEventResponse();
                typedResponse.log = log;
                typedResponse._requestId = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._fee = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashOutSuccessEventResponse> openCashOutSuccessEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHOUTSUCCESS_EVENT));
        return openCashOutSuccessEventObservable(filter);
    }

    public List<OpenCashOutFailedEventResponse> getOpenCashOutFailedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPENCASHOUTFAILED_EVENT, transactionReceipt);
        ArrayList<OpenCashOutFailedEventResponse> responses = new ArrayList<OpenCashOutFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OpenCashOutFailedEventResponse typedResponse = new OpenCashOutFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._requestId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OpenCashOutFailedEventResponse> openCashOutFailedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, OpenCashOutFailedEventResponse>() {
            @Override
            public OpenCashOutFailedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OPENCASHOUTFAILED_EVENT, log);
                OpenCashOutFailedEventResponse typedResponse = new OpenCashOutFailedEventResponse();
                typedResponse.log = log;
                typedResponse._kioskId = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._requestId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<OpenCashOutFailedEventResponse> openCashOutFailedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OPENCASHOUTFAILED_EVENT));
        return openCashOutFailedEventObservable(filter);
    }

    public List<ValidateCashOutAcceptedEventResponse> getValidateCashOutAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATECASHOUTACCEPTED_EVENT, transactionReceipt);
        ArrayList<ValidateCashOutAcceptedEventResponse> responses = new ArrayList<ValidateCashOutAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValidateCashOutAcceptedEventResponse typedResponse = new ValidateCashOutAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ValidateCashOutAcceptedEventResponse> validateCashOutAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ValidateCashOutAcceptedEventResponse>() {
            @Override
            public ValidateCashOutAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATECASHOUTACCEPTED_EVENT, log);
                ValidateCashOutAcceptedEventResponse typedResponse = new ValidateCashOutAcceptedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<ValidateCashOutAcceptedEventResponse> validateCashOutAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATECASHOUTACCEPTED_EVENT));
        return validateCashOutAcceptedEventObservable(filter);
    }

    public List<ValidateCashOutSuccessEventResponse> getValidateCashOutSuccessEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATECASHOUTSUCCESS_EVENT, transactionReceipt);
        ArrayList<ValidateCashOutSuccessEventResponse> responses = new ArrayList<ValidateCashOutSuccessEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValidateCashOutSuccessEventResponse typedResponse = new ValidateCashOutSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ValidateCashOutSuccessEventResponse> validateCashOutSuccessEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ValidateCashOutSuccessEventResponse>() {
            @Override
            public ValidateCashOutSuccessEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATECASHOUTSUCCESS_EVENT, log);
                ValidateCashOutSuccessEventResponse typedResponse = new ValidateCashOutSuccessEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ValidateCashOutSuccessEventResponse> validateCashOutSuccessEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATECASHOUTSUCCESS_EVENT));
        return validateCashOutSuccessEventObservable(filter);
    }

    public List<ValidateCashOutFailedEventResponse> getValidateCashOutFailedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VALIDATECASHOUTFAILED_EVENT, transactionReceipt);
        ArrayList<ValidateCashOutFailedEventResponse> responses = new ArrayList<ValidateCashOutFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ValidateCashOutFailedEventResponse typedResponse = new ValidateCashOutFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ValidateCashOutFailedEventResponse> validateCashOutFailedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ValidateCashOutFailedEventResponse>() {
            @Override
            public ValidateCashOutFailedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VALIDATECASHOUTFAILED_EVENT, log);
                ValidateCashOutFailedEventResponse typedResponse = new ValidateCashOutFailedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ValidateCashOutFailedEventResponse> validateCashOutFailedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VALIDATECASHOUTFAILED_EVENT));
        return validateCashOutFailedEventObservable(filter);
    }

    public List<CloseCashOutAcceptedEventResponse> getCloseCashOutAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHOUTACCEPTED_EVENT, transactionReceipt);
        ArrayList<CloseCashOutAcceptedEventResponse> responses = new ArrayList<CloseCashOutAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashOutAcceptedEventResponse typedResponse = new CloseCashOutAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashOutAcceptedEventResponse> closeCashOutAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashOutAcceptedEventResponse>() {
            @Override
            public CloseCashOutAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHOUTACCEPTED_EVENT, log);
                CloseCashOutAcceptedEventResponse typedResponse = new CloseCashOutAcceptedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashOutAcceptedEventResponse> closeCashOutAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHOUTACCEPTED_EVENT));
        return closeCashOutAcceptedEventObservable(filter);
    }

    public List<CloseCashOutSuccessEventResponse> getCloseCashOutSuccessEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHOUTSUCCESS_EVENT, transactionReceipt);
        ArrayList<CloseCashOutSuccessEventResponse> responses = new ArrayList<CloseCashOutSuccessEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashOutSuccessEventResponse typedResponse = new CloseCashOutSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashOutSuccessEventResponse> closeCashOutSuccessEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashOutSuccessEventResponse>() {
            @Override
            public CloseCashOutSuccessEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHOUTSUCCESS_EVENT, log);
                CloseCashOutSuccessEventResponse typedResponse = new CloseCashOutSuccessEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashOutSuccessEventResponse> closeCashOutSuccessEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHOUTSUCCESS_EVENT));
        return closeCashOutSuccessEventObservable(filter);
    }

    public List<CloseCashOutFailedEventResponse> getCloseCashOutFailedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSECASHOUTFAILED_EVENT, transactionReceipt);
        ArrayList<CloseCashOutFailedEventResponse> responses = new ArrayList<CloseCashOutFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseCashOutFailedEventResponse typedResponse = new CloseCashOutFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseCashOutFailedEventResponse> closeCashOutFailedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseCashOutFailedEventResponse>() {
            @Override
            public CloseCashOutFailedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSECASHOUTFAILED_EVENT, log);
                CloseCashOutFailedEventResponse typedResponse = new CloseCashOutFailedEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseCashOutFailedEventResponse> closeCashOutFailedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSECASHOUTFAILED_EVENT));
        return closeCashOutFailedEventObservable(filter);
    }

    public List<RollbackCashOutAcceptedEventResponse> getRollbackCashOutAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLLBACKCASHOUTACCEPTED_EVENT, transactionReceipt);
        ArrayList<RollbackCashOutAcceptedEventResponse> responses = new ArrayList<RollbackCashOutAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RollbackCashOutAcceptedEventResponse typedResponse = new RollbackCashOutAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RollbackCashOutAcceptedEventResponse> rollbackCashOutAcceptedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RollbackCashOutAcceptedEventResponse>() {
            @Override
            public RollbackCashOutAcceptedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLLBACKCASHOUTACCEPTED_EVENT, log);
                RollbackCashOutAcceptedEventResponse typedResponse = new RollbackCashOutAcceptedEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<RollbackCashOutAcceptedEventResponse> rollbackCashOutAcceptedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLLBACKCASHOUTACCEPTED_EVENT));
        return rollbackCashOutAcceptedEventObservable(filter);
    }

    public List<RollbackCashOutSuccessEventResponse> getRollbackCashOutSuccessEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLLBACKCASHOUTSUCCESS_EVENT, transactionReceipt);
        ArrayList<RollbackCashOutSuccessEventResponse> responses = new ArrayList<RollbackCashOutSuccessEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RollbackCashOutSuccessEventResponse typedResponse = new RollbackCashOutSuccessEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RollbackCashOutSuccessEventResponse> rollbackCashOutSuccessEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RollbackCashOutSuccessEventResponse>() {
            @Override
            public RollbackCashOutSuccessEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLLBACKCASHOUTSUCCESS_EVENT, log);
                RollbackCashOutSuccessEventResponse typedResponse = new RollbackCashOutSuccessEventResponse();
                typedResponse.log = log;
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RollbackCashOutSuccessEventResponse> rollbackCashOutSuccessEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLLBACKCASHOUTSUCCESS_EVENT));
        return rollbackCashOutSuccessEventObservable(filter);
    }

    public List<RollbackCashOutFailedEventResponse> getRollbackCashOutFailedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ROLLBACKCASHOUTFAILED_EVENT, transactionReceipt);
        ArrayList<RollbackCashOutFailedEventResponse> responses = new ArrayList<RollbackCashOutFailedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RollbackCashOutFailedEventResponse typedResponse = new RollbackCashOutFailedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RollbackCashOutFailedEventResponse> rollbackCashOutFailedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RollbackCashOutFailedEventResponse>() {
            @Override
            public RollbackCashOutFailedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ROLLBACKCASHOUTFAILED_EVENT, log);
                RollbackCashOutFailedEventResponse typedResponse = new RollbackCashOutFailedEventResponse();
                typedResponse.log = log;
                typedResponse._cashOutId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RollbackCashOutFailedEventResponse> rollbackCashOutFailedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLLBACKCASHOUTFAILED_EVENT));
        return rollbackCashOutFailedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> openCashOut(String _requestId, String _kioskId, BigInteger _toWithdraw, List<BigInteger> _fees, List<String> _parties) {
        final Function function = new Function(
                FUNC_OPENCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_requestId), 
                new org.web3j.abi.datatypes.Utf8String(_kioskId), 
                new org.web3j.abi.datatypes.generated.Uint256(_toWithdraw), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_fees, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_parties, org.web3j.abi.datatypes.Address.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __successOpenCashOut(String _requestId, String _kioskId, BigInteger _cashOutId, BigInteger _fee) {
        final Function function = new Function(
                FUNC___SUCCESSOPENCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_requestId), 
                new org.web3j.abi.datatypes.Utf8String(_kioskId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId), 
                new org.web3j.abi.datatypes.generated.Uint256(_fee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __failOpenCashOut(String _requestId, String _kioskId) {
        final Function function = new Function(
                FUNC___FAILOPENCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_requestId), 
                new org.web3j.abi.datatypes.Utf8String(_kioskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> validateCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC_VALIDATECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __successValidateCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___SUCCESSVALIDATECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __failValidateCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___FAILVALIDATECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC_CLOSECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __successCloseCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___SUCCESSCLOSECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __failCloseCashOut(BigInteger _sessionId, BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___FAILCLOSECASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> rollbackCashOut(BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC_ROLLBACKCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __successRollbackCashOut(BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___SUCCESSROLLBACKCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> __failRollbackCashOut(BigInteger _cashOutId) {
        final Function function = new Function(
                FUNC___FAILROLLBACKCASHOUT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_cashOutId)), 
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

    public static class OpenCashOutAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;
    }

    public static class OpenCashOutSuccessEventResponse implements CapitalHeroEvent {
        public Log log;

        public String _requestId;

        public String _kioskId;

        public BigInteger _cashOutId;

        public BigInteger _fee;
    }

    public static class OpenCashOutFailedEventResponse implements CapitalHeroEvent {
        public Log log;

        public String _kioskId;

        public String _requestId;
    }

    public static class ValidateCashOutAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;
    }

    public static class ValidateCashOutSuccessEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashOutId;
    }

    public static class ValidateCashOutFailedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashOutId;
    }

    public static class CloseCashOutAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;
    }

    public static class CloseCashOutSuccessEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashOutId;
    }

    public static class CloseCashOutFailedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _sessionId;

        public BigInteger _cashOutId;
    }

    public static class RollbackCashOutAcceptedEventResponse implements CapitalHeroEvent {
        public Log log;
    }

    public static class RollbackCashOutSuccessEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _cashOutId;
    }

    public static class RollbackCashOutFailedEventResponse implements CapitalHeroEvent {
        public Log log;

        public BigInteger _cashOutId;
    }
}
