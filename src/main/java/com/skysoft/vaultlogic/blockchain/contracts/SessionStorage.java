package com.skysoft.vaultlogic.blockchain.contracts;

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
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
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
public class SessionStorage extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806117ef833981018060405281019080805190602001909291905050508060008173ffffffffffffffffffffffffffffffffffffffff16141515156100c3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c529610157610261640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156101f55780820151818401526020810190506101da565b50505050905090810190601f1680156102225780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561024257600080fd5b505af1158015610256573d6000803e3d6000fd5b50505050505061029e565b60606040805190810160405280601281526020017f73657373696f6e2d7265706f7369746f72790000000000000000000000000000815250905090565b611542806102ad6000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063402ff0db146100935780635c622a0e146101475780637e55698e1461018857806382e408131461022e578063901135b714610265578063985d7b7d146103125780639d46ec8714610353578063df57082114610400575b600080fd5b34801561009f57600080fd5b506100be60048036038101908080359060200190929190505050610487565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561010a5780820151818401526020810190506100ef565b50505050905090810190601f1680156101375780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b34801561015357600080fd5b5061017260048036038101908080359060200190929190505050610500565b6040518082815260200191505060405180910390f35b34801561019457600080fd5b506101b36004803603810190808035906020019092919050505061056f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101f35780820151818401526020810190506101d8565b50505050905090810190601f1680156102205780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561023a57600080fd5b5061026360048036038101908080359060200190929190803590602001909291905050506105de565b005b34801561027157600080fd5b5061029060048036038101908080359060200190929190505050610735565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156102d65780820151818401526020810190506102bb565b50505050905090810190601f1680156103035780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561031e57600080fd5b5061033d60048036038101908080359060200190929190505050610754565b6040518082815260200191505060405180910390f35b34801561035f57600080fd5b5061037e600480360381019080803590602001909291905050506107c3565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156103c45780820151818401526020810190506103a9565b50505050905090810190601f1680156103f15780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561040c57600080fd5b506104856004803603810190808035906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506107e2565b005b6000606060006104f3846104cf6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff16610ad090919063ffffffff16565b9250925092509193909250565b6000610568826105446040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff16610e0990919063ffffffff16565b9050919050565b60606105d7826105b36040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff16610f0290919063ffffffff16565b9050919050565b61061c6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff1673__SessionLib____________________________63afd6ca25909184846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060006040518083038186803b1580156106da57600080fd5b505af41580156106ee573d6000803e3d6000fd5b505050507f150b944d2f668fb8c44fa57a4dd1c95f72d46a1efea6a92a497a002cf491f47b8282604051808381526020018281526020019250505060405180910390a15050565b6000606061074283610754565b915061074d8361056f565b9050915091565b60006107bc826107986040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff1661105190919063ffffffff16565b9050919050565b600060606107d083610500565b91506107db8361056f565b9050915091565b61084e848484846108276040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250610908565b73ffffffffffffffffffffffffffffffffffffffff1661114a90949392919063ffffffff16565b7f35f9f680a7ef4ece0ee1181934b06a99d8b8f014153d6629867b45f714f49a61848484846040518085815260200184815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156108c55780820151818401526020810190506108aa565b50505050905090810190601f1680156108f25780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a150505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156109b3578082015181840152602081019050610998565b50505050905090810190601f1680156109e05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156109ff57600080fd5b505af1158015610a13573d6000803e3d6000fd5b505050506040513d6020811015610a2957600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151515610acb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b919050565b6000606060008473ffffffffffffffffffffffffffffffffffffffff1663e82617fb610b316040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f69640000000000000000000081525087611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015610b8b57600080fd5b505af1158015610b9f573d6000803e3d6000fd5b505050506040513d6020811015610bb557600080fd5b810190808051906020019092919050505092508473ffffffffffffffffffffffffffffffffffffffff1663a209a29c610c236040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e000000000000000000000000000000000081525087611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b158015610c7d57600080fd5b505af1158015610c91573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015610cbb57600080fd5b810190808051640100000000811115610cd357600080fd5b82810190506020810184811115610ce957600080fd5b8151856001820283011164010000000082111715610d0657600080fd5b505092919050505091508473ffffffffffffffffffffffffffffffffffffffff1663e82617fb610d6b6040805190810160405280600e81526020017f73657373696f6e5f73746174757300000000000000000000000000000000000081525087611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015610dc557600080fd5b505af1158015610dd9573d6000803e3d6000fd5b505050506040513d6020811015610def57600080fd5b810190808051906020019092919050505090509250925092565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb610e666040805190810160405280600e81526020017f73657373696f6e5f73746174757300000000000000000000000000000000000081525085611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015610ec057600080fd5b505af1158015610ed4573d6000803e3d6000fd5b505050506040513d6020811015610eea57600080fd5b81019080805190602001909291905050505092915050565b60608273ffffffffffffffffffffffffffffffffffffffff1663a209a29c610f5f6040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e000000000000000000000000000000000081525085611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050600060405180830381600087803b158015610fb957600080fd5b505af1158015610fcd573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015610ff757600080fd5b81019080805164010000000081111561100f57600080fd5b8281019050602081018481111561102557600080fd5b815185600182028301116401000000008211171561104257600080fd5b50509291905050505092915050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb6110ae6040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f69640000000000000000000081525085611435565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561110857600080fd5b505af115801561111c573d6000803e3d6000fd5b505050506040513d602081101561113257600080fd5b81019080805190602001909291905050505092915050565b8473ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236111a56040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f69640000000000000000000081525087611435565b856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561120757600080fd5b505af115801561121b573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663f586606661127a6040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e000000000000000000000000000000000081525087611435565b846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b838110156112f45780820151818401526020810190506112d9565b50505050905090810190601f1680156113215780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561134157600080fd5b505af1158015611355573d6000803e3d6000fd5b505050508473ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236113b46040805190810160405280600e81526020017f73657373696f6e5f73746174757300000000000000000000000000000000000081525087611435565b836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561141657600080fd5b505af115801561142a573d6000803e3d6000fd5b505050505050505050565b600082826040516020018083805190602001908083835b602083101515611471578051825260208201915060208101905060208303925061144c565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156114e157805182526020820191506020810190506020830392506114bc565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209050929150505600a165627a7a72305820814ea5d164f478cd52f4a1fe53d83cf7305f12ebd833025fee2e1de9a4b26b0d0029";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_GETSESSION = "getSession";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_GETSTATUSANDXTOKEN = "getStatusAndXToken";

    public static final String FUNC_GETAPPID = "getAppId";

    public static final String FUNC_GETXTOKEN = "getXToken";

    public static final String FUNC_GETAPPIDANDXTOKEN = "getAppIdAndXToken";

    public static final String FUNC_SETSTATUS = "setStatus";

    public static final Event SAVED_EVENT = new Event("Saved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event STATUSUPDATED_EVENT = new Event("StatusUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x0faef70bc0c30b0d968e2a5b553b95c81dfb388f");
    }

    protected SessionStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<SessionStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String registryAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddress)));
        return deployRemoteCall(SessionStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String registryAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddress)));
        return deployRemoteCall(SessionStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<SavedEventResponse> getSavedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SAVED_EVENT, transactionReceipt);
        ArrayList<SavedEventResponse> responses = new ArrayList<SavedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SavedEventResponse typedResponse = new SavedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.xToken = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SavedEventResponse> savedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SavedEventResponse>() {
            @Override
            public SavedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SAVED_EVENT, log);
                SavedEventResponse typedResponse = new SavedEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.appId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.xToken = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SavedEventResponse> savedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SAVED_EVENT));
        return savedEventObservable(filter);
    }

    public List<StatusUpdatedEventResponse> getStatusUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(STATUSUPDATED_EVENT, transactionReceipt);
        ArrayList<StatusUpdatedEventResponse> responses = new ArrayList<StatusUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            StatusUpdatedEventResponse typedResponse = new StatusUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<StatusUpdatedEventResponse> statusUpdatedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, StatusUpdatedEventResponse>() {
            @Override
            public StatusUpdatedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(STATUSUPDATED_EVENT, log);
                StatusUpdatedEventResponse typedResponse = new StatusUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<StatusUpdatedEventResponse> statusUpdatedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(STATUSUPDATED_EVENT));
        return statusUpdatedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> save(BigInteger sessionId, BigInteger appId, String xToken, BigInteger status) {
        final Function function = new Function(
                FUNC_SAVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(xToken), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<BigInteger, String, BigInteger>> getSession(BigInteger index) {
        final Function function = new Function(FUNC_GETSESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<BigInteger, String, BigInteger>>(
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getStatus(BigInteger index) {
        final Function function = new Function(FUNC_GETSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> getStatusAndXToken(BigInteger index) {
        final Function function = new Function(FUNC_GETSTATUSANDXTOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getAppId(BigInteger index) {
        final Function function = new Function(FUNC_GETAPPID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getXToken(BigInteger index) {
        final Function function = new Function(FUNC_GETXTOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> getAppIdAndXToken(BigInteger index) {
        final Function function = new Function(FUNC_GETAPPIDANDXTOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setStatus(BigInteger index, BigInteger status) {
        final Function function = new Function(
                FUNC_SETSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index), 
                new org.web3j.abi.datatypes.generated.Uint256(status)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static SessionStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SessionStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class SavedEventResponse {
        public Log log;

        public BigInteger sessionId;

        public BigInteger appId;

        public String xToken;

        public BigInteger status;
    }

    public static class StatusUpdatedEventResponse {
        public Log log;

        public BigInteger index;

        public BigInteger status;
    }
}
