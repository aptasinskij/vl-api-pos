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
import org.web3j.abi.datatypes.generated.Uint256;
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
public class ParameterStorage extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50604051602080610a6c8339810180604052810190808051906020019092919050505080336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff1614151515610103576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296101996102a3640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b8381101561023757808201518184015260208101905061021c565b50505050905090810190601f1680156102645780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561028457600080fd5b505af1158015610298573d6000803e3d6000fd5b5050505050506102e0565b60606040805190810160405280601181526020017f706172616d657465722d73746f72616765000000000000000000000000000000815250905090565b61077d806102ef6000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680638da5cb5b1461005c5780639d98fd6b146100b3578063cc0a29e6146100e0575b600080fd5b34801561006857600080fd5b5061007161010b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100bf57600080fd5b506100de60048036038101908080359060200190929190505050610130565b005b3480156100ec57600080fd5b506100f5610199565b6040518082815260200191505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b610196816101726040805190810160405280600881526020017f64617461626173650000000000000000000000000000000000000000000000008152506101fc565b73ffffffffffffffffffffffffffffffffffffffff166103c590919063ffffffff16565b50565b60006101f76101dc6040805190810160405280600881526020017f64617461626173650000000000000000000000000000000000000000000000008152506101fc565b73ffffffffffffffffffffffffffffffffffffffff1661057b565b905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156102a857808201518184015260208101905061028d565b50505050905090810190601f1680156102d55780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156102f457600080fd5b505af1158015610308573d6000803e3d6000fd5b505050506040513d602081101561031e57600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff16141515156103c0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b919050565b8173ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236040805190810160405280601781526020017f7661756c745f6c6f6769635f6665655f70657263656e740000000000000000008152506040516020018080602001828103825283818151815260200191508051906020019080838360005b8381101561045a57808201518184015260208101905061043f565b50505050905090810190601f1680156104875780820380516001836020036101000a031916815260200191505b50925050506040516020818303038152906040526040518082805190602001908083835b6020831015156104d057805182526020820191506020810190506020830392506104ab565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020836040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561055f57600080fd5b505af1158015610573573d6000803e3d6000fd5b505050505050565b60008173ffffffffffffffffffffffffffffffffffffffff1663e82617fb6040805190810160405280601781526020017f7661756c745f6c6f6769635f6665655f70657263656e740000000000000000008152506040516020018080602001828103825283818151815260200191508051906020019080838360005b838110156106125780820151818401526020810190506105f7565b50505050905090810190601f16801561063f5780820380516001836020036101000a031916815260200191505b50925050506040516020818303038152906040526040518082805190602001908083835b6020831015156106885780518252602082019150602081019050602083039250610663565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561070f57600080fd5b505af1158015610723573d6000803e3d6000fd5b505050506040513d602081101561073957600080fd5b810190808051906020019092919050505090509190505600a165627a7a72305820b19289023080c0f8fcf59933e1c1aa07d2e5ef4a23208d9933df2fb6a53fba280029";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETVLFEE = "setVLFee";

    public static final String FUNC_GETVLFEE = "getVLFee";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x9d40ad39b007bfc5fb739f0a5f1489b370ddef04");
    }

    protected ParameterStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ParameterStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<ParameterStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String registryAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddress)));
        return deployRemoteCall(ParameterStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<ParameterStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String registryAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(registryAddress)));
        return deployRemoteCall(ParameterStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> setVLFee(BigInteger percent) {
        final Function function = new Function(
                FUNC_SETVLFEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(percent)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getVLFee() {
        final Function function = new Function(FUNC_GETVLFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static ParameterStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ParameterStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ParameterStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ParameterStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface ParameterStorageEvent extends SmartContractEvent {
    }
}
