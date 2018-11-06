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
    private static final String BINARY = "0x608060405234801561001057600080fd5b50604051602080610a93833981016040818152915182820190925260138082527f6170706c69636174696f6e2d6d616e61676572000000000000000000000000006020830190815260008054600160a060020a03191633179055839291610079916001916102a7565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156100d257600080fd5b505af11580156100e6573d6000803e3d6000fd5b505050506040513d60208110156100fc57600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b15801561016f57600080fd5b505af1158015610183573d6000803e3d6000fd5b505050506040513d602081101561019957600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be939290918291604490910190849080156102685780601f1061023d57610100808354040283529160200191610268565b820191906000526020600020905b81548152906001019060200180831161024b57829003601f168201915b505092505050600060405180830381600087803b15801561028857600080fd5b505af115801561029c573d6000803e3d6000fd5b505050505050610342565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102e857805160ff1916838001178555610315565b82800160010185558215610315579182015b828111156103155782518255916020019190600101906102fa565b50610321929150610325565b5090565b61033f91905b80821115610321576000815560010161032b565b90565b610742806103516000396000f3006080604052600436106100535763ffffffff60e060020a60003504166306fdde03811461005857806341c0e1b5146100e2578063876cc3f9146100f95780638da5cb5b14610111578063e7ae017c14610142575b600080fd5b34801561006457600080fd5b5061006d6101f8565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100a757818101518382015260200161008f565b50505050905090810190601f1680156100d45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156100ee57600080fd5b506100f7610285565b005b34801561010557600080fd5b506100f7600435610302565b34801561011d57600080fd5b5061012661048b565b60408051600160a060020a039092168252519081900360200190f35b34801561014e57600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526100f795833595369560449491939091019190819084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b600160a060020a038b35169b909a90999401975091955091820193509150819084018382808284375094975050509235600160a060020a0316935061049a92505050565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561027d5780601f106102525761010080835404028352916020019161027d565b820191906000526020600020905b81548152906001019060200180831161026057829003601f168201915b505050505081565b600054600160a060020a031632146102fe57604080517f08c379a000000000000000000000000000000000000000000000000000000000815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b600254604080518082018252601381527f6170706c69636174696f6e2d73746f7261676500000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156103a757818101518382015260200161038f565b50505050905090810190601f1680156103d45780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156103f357600080fd5b505af1158015610407573d6000803e3d6000fd5b505050506040513d602081101561041d57600080fd5b5051600160a060020a031663f004a7e48260016040518363ffffffff1660e060020a0281526004018083815260200182815260200192505050600060405180830381600087803b15801561047057600080fd5b505af1158015610484573d6000803e3d6000fd5b5050505050565b600054600160a060020a031681565b600254604080518082018252601381527f6170706c69636174696f6e2d73746f7261676500000000000000000000000000602080830191825292517f693ec85e00000000000000000000000000000000000000000000000000000000815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561053f578181015183820152602001610527565b50505050905090810190601f16801561056c5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561058b57600080fd5b505af115801561059f573d6000803e3d6000fd5b505050506040513d60208110156105b557600080fd5b5051600160a060020a031663edcce1b38686868686600060405160e060020a63ffffffff891602815260048101878152600160a060020a0380871660448401528416608483015260a4820183905260c060248301908152875160c4840152875191929091606482019160e4019060208a019080838360005b8381101561064557818101518382015260200161062d565b50505050905090810190601f1680156106725780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b838110156106a557818101518382015260200161068d565b50505050905090810190601f1680156106d25780820380516001836020036101000a031916815260200191505b5098505050505050505050600060405180830381600087803b1580156106f757600080fd5b505af115801561070b573d6000803e3d6000fd5b5050505050505050505600a165627a7a7230582073aa4c80fbe16e2fd44f95957f999c43805819db8efeaf1faf7311424334c08d0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REGISTERAPPLICATION = "registerApplication";

    public static final String FUNC_ENABLEAPPLICATION = "enableApplication";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0x18403230af6dae16749d37f5d048060173f3ac60");
    }

    protected ApplicationManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ApplicationManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<ApplicationManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(ApplicationManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<ApplicationManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
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
