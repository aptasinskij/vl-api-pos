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
import org.web3j.abi.datatypes.Utf8String;
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
    private static final String BINARY = "0x608060405234801561001057600080fd5b50604051602080610942833981016040818152915182820190925260118082527f706172616d657465722d73746f726167650000000000000000000000000000006020830190815260008054600160a060020a03191633179055839291610079916001916102a7565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100d257600080fd5b505af11580156100e6573d6000803e3d6000fd5b505050506040513d60208110156100fc57600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b15801561016f57600080fd5b505af1158015610183573d6000803e3d6000fd5b505050506040513d602081101561019957600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be939290918291604490910190849080156102685780601f1061023d57610100808354040283529160200191610268565b820191906000526020600020905b81548152906001019060200180831161024b57829003601f168201915b505092505050600060405180830381600087803b15801561028857600080fd5b505af115801561029c573d6000803e3d6000fd5b505050505050610342565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e857805160ff1916838001178555610315565b82800160010185558215610315579182015b828111156103155782518255916020019190600101906102fa565b50610321929150610325565b5090565b61033f91905b80821115610321576000815560010161032b565b90565b6105f1806103516000396000f3006080604052600436106100535763ffffffff60e060020a60003504166306fdde03811461005857806341c0e1b5146100e25780638da5cb5b146100f95780639d98fd6b1461012a578063cc0a29e614610142575b600080fd5b34801561006457600080fd5b5061006d610169565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100a757818101518382015260200161008f565b50505050905090810190601f1680156100d45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100ee57600080fd5b506100f76101f6565b005b34801561010557600080fd5b5061010e610273565b60408051600160a060020a039092168252519081900360200190f35b34801561013657600080fd5b506100f7600435610282565b34801561014e57600080fd5b506101576102a1565b60408051918252519081900360200190f35b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156101ee5780601f106101c3576101008083540402835291602001916101ee565b820191906000526020600020905b8154815290600101906020018083116101d157829003601f168201915b505050505081565b600054600160a060020a0316321461026f57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600054600160a060020a031681565b60035461029e90600160a060020a03168263ffffffff6102be16565b50565b6003546000906102b990600160a060020a031661043b565b905090565b81600160a060020a031663ffb4f6236040805190810160405280601781526020017f7661756c745f6c6f6769635f6665655f70657263656e740000000000000000008152506040516020018080602001828103825283818151815260200191508051906020019080838360005b8381101561034357818101518382015260200161032b565b50505050905090810190601f1680156103705780820380516001836020036101000a031916815260200191505b50925050506040516020818303038152906040526040518082805190602001908083835b602083106103b35780518252601f199092019160209182019101610394565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a028252600482015260248101889052915160448084019550600094509092839003019050818387803b15801561041f57600080fd5b505af1158015610433573d6000803e3d6000fd5b505050505050565b600081600160a060020a031663e82617fb6040805190810160405280601781526020017f7661756c745f6c6f6769635f6665655f70657263656e740000000000000000008152506040516020018080602001828103825283818151815260200191508051906020019080838360005b838110156104c25781810151838201526020016104aa565b50505050905090810190601f1680156104ef5780820380516001836020036101000a031916815260200191505b50925050506040516020818303038152906040526040518082805190602001908083835b602083106105325780518252601f199092019160209182019101610513565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561059357600080fd5b505af11580156105a7573d6000803e3d6000fd5b505050506040513d60208110156105bd57600080fd5b5051929150505600a165627a7a72305820c0fa166e8233844a2f20ea56fc5d0f69b38061ac31cd412c6296eb152bd47a040029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETVLFEE = "setVLFee";

    public static final String FUNC_GETVLFEE = "getVLFee";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0x03cc32ae2040667c6c32e8defd5f5f80307e9896");
    }

    protected ParameterStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ParameterStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<ParameterStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(ParameterStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<ParameterStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
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
