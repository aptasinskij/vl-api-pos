package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.contracts.SmartContractEvent;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class ApplicationManager extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b506040516020806109b78339810180604052810190808051906020019092919050505080336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff1614151515610103576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296101996102a3640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561023757808201518184015260208101905061021c565b50505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561028457600080fd5b505af1158015610298573d6000803e3d6000fd5b5050505050506102e0565b60606040805190810160405280601381526020017f6170706c69636174696f6e2d6d616e6167657200000000000000000000000000815250905090565b6106c8806102ef6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063876cc3f91461005c5780638da5cb5b14610089578063e7ae017c146100e0575b600080fd5b34801561006857600080fd5b50610087600480360381019080803590602001909291905050506101d9565b005b34801561009557600080fd5b5061009e61027e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100ec57600080fd5b506101d760048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102a3565b005b6101e161048e565b73ffffffffffffffffffffffffffffffffffffffff1663f004a7e4826001600281111561020a57fe5b6040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b15801561026357600080fd5b505af1158015610277573d6000803e3d6000fd5b5050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6102ab61048e565b73ffffffffffffffffffffffffffffffffffffffff1663edcce1b38686868686600060028111156102d857fe5b6040518763ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180878152602001806020018673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001806020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001848152602001838103835288818151815260200191508051906020019080838360005b838110156103b757808201518184015260208101905061039c565b50505050905090810190601f1680156103e45780820380516001836020036101000a031916815260200191505b50838103825286818151815260200191508051906020019080838360005b8381101561041d578082015181840152602081019050610402565b50505050905090810190601f16801561044a5780820380516001836020036101000a031916815260200191505b5098505050505050505050600060405180830381600087803b15801561046f57600080fd5b505af1158015610483573d6000803e3d6000fd5b505050505050505050565b60006104ce6040805190810160405280601381526020017f6170706c69636174696f6e2d73746f72616765000000000000000000000000008152506104d3565b905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b8381101561057f578082015181840152602081019050610564565b50505050905090810190601f1680156105ac5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156105cb57600080fd5b505af11580156105df573d6000803e3d6000fd5b505050506040513d60208110156105f557600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151515610697576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b9190505600a165627a7a72305820e6f47882c99cfe1fc156653350923ccdd03f9d69fe27d7c388dd75dbd0e42c020029";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTERAPPLICATION = "registerApplication";

    public static final String FUNC_ENABLEAPPLICATION = "enableApplication";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("77142", "0x9867e207cf9822e574efc22008fa621e4326c333");
    }

    protected ApplicationManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ApplicationManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<ApplicationManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(ApplicationManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<ApplicationManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(ApplicationManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> registerApplication(BigInteger appId, String name, String owner, String url, String appAddr) {
        final Function function = new Function(
                FUNC_REGISTERAPPLICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Address(owner), 
                new org.web3j.abi.datatypes.Utf8String(url), 
                new org.web3j.abi.datatypes.Address(appAddr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> enableApplication(BigInteger applicationId) {
        final Function function = new Function(
                FUNC_ENABLEAPPLICATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(applicationId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static ApplicationManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ApplicationManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ApplicationManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ApplicationManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface ApplicationManagerEvent extends SmartContractEvent {
    }
}
