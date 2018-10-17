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
import org.web3j.abi.datatypes.Address;
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
public class SessionOracle extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806109db833981018060405281019080805190602001909291905050508060006040805190810160405280600e81526020017f73657373696f6e2d6f7261636c65000000000000000000000000000000000000815250336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600190805190602001906100c29291906105a4565b50508173ffffffffffffffffffffffffffffffffffffffff1663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561012857600080fd5b505af115801561013c573d6000803e3d6000fd5b505050506040513d602081101561015257600080fd5b8101908080519060200190929190505050600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663d0496d6a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561020757600080fd5b505af115801561021b573d6000803e3d6000fd5b505050506040513d602081101561023157600080fd5b8101908080519060200190929190505050600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e60016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103765780601f1061034b57610100808354040283529160200191610376565b820191906000526020600020905b81548152906001019060200180831161035957829003601f168201915b505092505050602060405180830381600087803b15801561039657600080fd5b505af11580156103aa573d6000803e3d6000fd5b505050506040513d60208110156103c057600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16141515610470578073ffffffffffffffffffffffffffffffffffffffff166341c0e1b56040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561045757600080fd5b505af115801561046b573d6000803e3d6000fd5b505050505b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f2c298be60016040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156105645780601f1061053957610100808354040283529160200191610564565b820191906000526020600020905b81548152906001019060200180831161054757829003601f168201915b505092505050600060405180830381600087803b15801561058457600080fd5b505af1158015610598573d6000803e3d6000fd5b50505050505050610649565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106105e557805160ff1916838001178555610613565b82800160010185558215610613579182015b828111156106125782518255916020019190600101906105f7565b5b5090506106209190610624565b5090565b61064691905b8082111561064257600081600090555060010161062a565b5090565b90565b610383806106586000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde031461006757806341c0e1b5146100f75780638da5cb5b1461010e578063a3bfdf4714610165575b600080fd5b34801561007357600080fd5b5061007c610192565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100bc5780820151818401526020810190506100a1565b50505050905090810190601f1680156100e95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561010357600080fd5b5061010c610230565b005b34801561011a57600080fd5b506101236102f8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561017157600080fd5b506101906004803603810190808035906020019092919050505061031d565b005b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102285780601f106101fd57610100808354040283529160200191610228565b820191906000526020600020905b81548152906001019060200180831161020b57829003601f168201915b505050505081565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163273ffffffffffffffffffffffffffffffffffffffff161415156102f4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f6f6e6c79206f776e657220616c6c6f776564000000000000000000000000000081525060200191505060405180910390fd5b6000ff5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b7f71c2e31dde72b491e4b02270b0b15a6a4fc0dd6d696c8d0f43604a92ff109193816040518082815260200191505060405180910390a1505600a165627a7a72305820123ff1a32d05a9b781ebcfbf8c554b0bd1e143fbfe76f88982bcc73a9bd683870029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final Event CLOSESESSION_EVENT = new Event("CloseSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("77142", "0xf22d71de95f1e1e1f56bb2421eaa2e78292ea36f");
    }

    protected SessionOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(SessionOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(SessionOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<CloseSessionEventResponse> getCloseSessionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CLOSESESSION_EVENT, transactionReceipt);
        ArrayList<CloseSessionEventResponse> responses = new ArrayList<CloseSessionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CloseSessionEventResponse typedResponse = new CloseSessionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CloseSessionEventResponse> closeSessionEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CloseSessionEventResponse>() {
            @Override
            public CloseSessionEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CLOSESESSION_EVENT, log);
                CloseSessionEventResponse typedResponse = new CloseSessionEventResponse();
                typedResponse.log = log;
                typedResponse.sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CloseSessionEventResponse> closeSessionEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CLOSESESSION_EVENT));
        return closeSessionEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> closeSession(BigInteger sessionId) {
        final Function function = new Function(
                FUNC_CLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static SessionOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SessionOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface SessionOracleEvent extends SmartContractEvent {
    }

    public static class CloseSessionEventResponse implements SessionOracleEvent {
        public Log log;

        public BigInteger sessionId;
    }
}
