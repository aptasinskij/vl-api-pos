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
public class PrinterOracle extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b50604051602080620021eb8339810160408181529151828201909252600e8082527f7072696e7465722d6f7261636c650000000000000000000000000000000000006020830190815260008054600160a060020a031916331790558392916200007d91600191620002b8565b505080600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000d757600080fd5b505af1158015620000ec573d6000803e3d6000fd5b505050506040513d60208110156200010357600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519183169163d0496d6a916004808201926020929091908290030181600087803b1580156200017757600080fd5b505af11580156200018c573d6000803e3d6000fd5b505050506040513d6020811015620001a357600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363f2c298be93929091829160449091019084908015620002765780601f106200024a5761010080835404028352916020019162000276565b820191906000526020600020905b8154815290600101906020018083116200025857829003601f168201915b505092505050600060405180830381600087803b1580156200029757600080fd5b505af1158015620002ac573d6000803e3d6000fd5b5050505050506200035d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620002fb57805160ff19168380011785556200032b565b828001600101855582156200032b579182015b828111156200032b5782518255916020019190600101906200030e565b50620003399291506200033d565b5090565b6200035a91905b8082111562000339576000815560010162000344565b90565b611e7e806200036d6000396000f30060806040526004361061007e5763ffffffff60e060020a6000350416627a5983811461008357806306fdde031461012157806341c0e1b5146101ab5780634693b378146101c05780638d79e81c146101d85780638da5cb5b14610204578063a30140a614610235578063c642b4ae1461024d578063e330bf7314610265575b600080fd5b34801561008f57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261011f95833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061027d9650505050505050565b005b34801561012d57600080fd5b50610136610515565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610170578181015183820152602001610158565b50505050905090810190601f16801561019d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101b757600080fd5b5061011f6105a2565b3480156101cc57600080fd5b5061011f6004356105f6565b3480156101e457600080fd5b506101f06004356107b9565b604080519115158252519081900360200190f35b34801561021057600080fd5b50610219610971565b60408051600160a060020a039092168252519081900360200190f35b34801561024157600080fd5b506101f0600435610980565b34801561025957600080fd5b5061011f6004356109f4565b34801561027157600080fd5b5061011f600435610b9c565b600054600160a060020a031633146102cd576040805160e560020a62461bcd0281526020600482015260126024820152600080516020611e33833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020611e138339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b8381101561034a578181015183820152602001610332565b50505050905090810190601f1680156103775780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561039657600080fd5b505af11580156103aa573d6000803e3d6000fd5b505050506040513d60208110156103c057600080fd5b50516040517f07fac49600000000000000000000000000000000000000000000000000000000815260048101858152606060248301908152855160648401528551600160a060020a03909416936307fac4969388938893889391929091604482019160840190602087019080838360005b83811015610449578181015183820152602001610431565b50505050905090810190601f1680156104765780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156104a9578181015183820152602001610491565b50505050905090810190601f1680156104d65780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1580156104f857600080fd5b505af115801561050c573d6000803e3d6000fd5b50505050505050565b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152929183018282801561059a5780601f1061056f5761010080835404028352916020019161059a565b820191906000526020600020905b81548152906001019060200180831161057d57829003601f168201915b505050505081565b600054600160a060020a031632146105f2576040805160e560020a62461bcd0281526020600482015260126024820152600080516020611e33833981519152604482015290519081900360640190fd5b6000ff5b600054600160a060020a03163314610646576040805160e560020a62461bcd0281526020600482015260126024820152600080516020611e33833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020611e138339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b838110156106c35781810151838201526020016106ab565b50505050905090810190601f1680156106f05780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561070f57600080fd5b505af1158015610723573d6000803e3d6000fd5b505050506040513d602081101561073957600080fd5b5051604080517f76eefe27000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916376eefe279160248082019260009290919082900301818387803b15801561079e57600080fd5b505af11580156107b2573d6000803e3d6000fd5b5050505050565b60006107c3611dad565b6003546107df90600160a060020a03168463ffffffff610d4416565b90507f2002963a3c2b2da8c4d63a883c9fbbec67feac2bdafde7da25157ecf89f9081383826020015183604001518460600151856080015160405180868152602001858152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b83811015610868578181015183820152602001610850565b50505050905090810190601f1680156108955780820380516001836020036101000a031916815260200191505b50848103835286518152865160209182019188019080838360005b838110156108c85781810151838201526020016108b0565b50505050905090810190601f1680156108f55780820380516001836020036101000a031916815260200191505b50848103825285518152855160209182019187019080838360005b83811015610928578181015183820152602001610910565b50505050905090810190601f1680156109555780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a150600192915050565b600054600160a060020a031681565b600061098a611deb565b6003546109a690600160a060020a03168463ffffffff6117ad16565b90507f62b01c50ba4b1e21a4796deffb7e07540187fffcca5e57189085a08fbfaaaef7838260200151604051808381526020018281526020019250505060405180910390a150600192915050565b600054600160a060020a03163314610a44576040805160e560020a62461bcd0281526020600482015260126024820152600080516020611e33833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020611e138339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610ac1578181015183820152602001610aa9565b50505050905090810190601f168015610aee5780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610b0d57600080fd5b505af1158015610b21573d6000803e3d6000fd5b505050506040513d6020811015610b3757600080fd5b5051604080517f31e2ab8a000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916331e2ab8a9160248082019260009290919082900301818387803b15801561079e57600080fd5b600054600160a060020a03163314610bec576040805160e560020a62461bcd0281526020600482015260126024820152600080516020611e33833981519152604482015290519081900360640190fd5b600254604080518082018252600f8152600080516020611e138339815191526020808301918252925160e160020a63349f642f02815260048101938452825160248201528251600160a060020a039095169463693ec85e949283926044019180838360005b83811015610c69578181015183820152602001610c51565b50505050905090810190601f168015610c965780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015610cb557600080fd5b505af1158015610cc9573d6000803e3d6000fd5b505050506040513d6020811015610cdf57600080fd5b5051604080517f52e215ef000000000000000000000000000000000000000000000000000000008152600481018490529051600160a060020a03909216916352e215ef9160248082019260009290919082900301818387803b15801561079e57600080fd5b610d4c611dad565b610d568383611bc6565b1515610dac576040805160e560020a62461bcd02815260206004820152601b60248201527f72656365697074207072696e74206973206e6f74206578697374730000000000604482015290519081900360640190fd5b60e06040519081016040528083815260200184600160a060020a031663e82617fb6040805190810160405280602081526020017f726563656970742d7072696e742e73657373696f6e2e69643a75696e74323536815250866040516020018083805190602001908083835b60208310610e365780518252601f199092019160209182019101610e17565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610e965780518252601f199092019160209182019101610e77565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610ef757600080fd5b505af1158015610f0b573d6000803e3d6000fd5b505050506040513d6020811015610f2157600080fd5b50518152604080518082018252601f8082527f726563656970742d7072696e742e726563656970745f69643a737472696e67006020838101918252935194840194600160a060020a038a169463a209a29c94938a93929091019182918083835b60208310610fa05780518252601f199092019160209182019101610f81565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106110005780518252601f199092019160209182019101610fe1565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b15801561106557600080fd5b505af1158015611079573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f1916820160405260208110156110a257600080fd5b8101908080516401000000008111156110ba57600080fd5b820160208101848111156110cd57600080fd5b81516401000000008111828201871017156110e757600080fd5b50508452505060408051808201825260198082527f726563656970742d7072696e742e646174613a737472696e67000000000000006020838101918252935195840195600160a060020a038b16955063a209a29c948a9391019182918083835b602083106111665780518252601f199092019160209182019101611147565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106111c65780518252601f1990920191602091820191016111a7565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b15801561122b57600080fd5b505af115801561123f573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052602081101561126857600080fd5b81019080805164010000000081111561128057600080fd5b8201602081018481111561129357600080fd5b81516401000000008111828201871017156112ad57600080fd5b505084525050604080518082018252601b8082527f726563656970742d7072696e742e706172616d733a737472696e6700000000006020838101918252935195840195600160a060020a038b16955063a209a29c948a9391019182918083835b6020831061132c5780518252601f19909201916020918201910161130d565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b6020831061138c5780518252601f19909201916020918201910161136d565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b1580156113f157600080fd5b505af1158015611405573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052602081101561142e57600080fd5b81019080805164010000000081111561144657600080fd5b8201602081018481111561145957600080fd5b815164010000000081118282018710171561147357600080fd5b505084525050604080518082018252601e8082527f726563656970742d7072696e742e737563636573733a66756e6374696f6e00006020838101918252935195840195600160a060020a038b16955063f8b98ab1948a9391019182918083835b602083106114f25780518252601f1990920191602091820191016114d3565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106115525780518252601f199092019160209182019101611533565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b1580156115b357600080fd5b505af11580156115c7573d6000803e3d6000fd5b505050506040513d60208110156115dd57600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff68010000000000000000928390041617028152604080518082018252601b8082527f726563656970742d7072696e742e6661696c3a66756e6374696f6e00000000006020838101918252935194840194600160a060020a038a169463f8b98ab194938a93929091019182918083835b602083106116895780518252601f19909201916020918201910161166a565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106116e95780518252601f1990920191602091820191016116ca565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561174a57600080fd5b505af115801561175e573d6000803e3d6000fd5b505050506040513d602081101561177457600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff68010000000000000000928390041617029052905092915050565b6117b5611deb565b6117bf8383611d34565b1515611815576040805160e560020a62461bcd02815260206004820152601c60248201527f7265636569707420637265617465206973206e6f742065786973747300000000604482015290519081900360640190fd5b60806040519081016040528083815260200184600160a060020a031663e82617fb606060405190810160405280602181526020017f726563656970742d6372656174652e73657373696f6e2e69643a75696e74323581526020017f3600000000000000000000000000000000000000000000000000000000000000815250866040516020018083805190602001908083835b602083106118c65780518252601f1990920191602091820191016118a7565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106119265780518252601f199092019160209182019101611907565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561198757600080fd5b505af115801561199b573d6000803e3d6000fd5b505050506040513d60208110156119b157600080fd5b50518152604080518082018252601f8082527f726563656970742d6372656174652e737563636573733a66756e6374696f6e006020838101918252935194840194600160a060020a038a169463d8b85d4594938a93929091019182918083835b60208310611a305780518252601f199092019160209182019101611a11565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310611a905780518252601f199092019160209182019101611a71565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015611af157600080fd5b505af1158015611b05573d6000803e3d6000fd5b505050506040513d6020811015611b1b57600080fd5b50516401000000006c0100000000000000000000000082040263ffffffff68010000000000000000928390041617028152604080518082018252601c8082527f726563656970742d6372656174652e6661696c3a66756e6374696f6e000000006020838101918252935194840194600160a060020a038a169463f8b98ab194938a9392909101918291808383602083106116895780518252601f19909201916020918201910161166a565b600082600160a060020a03166317e7dd226040805190810160405280601c81526020017f726563656970742d7072696e742e6578697374733a626f6f6c65616e00000000815250846040516020018083805190602001908083835b60208310611c405780518252601f199092019160209182019101611c21565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310611ca05780518252601f199092019160209182019101611c81565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015611d0157600080fd5b505af1158015611d15573d6000803e3d6000fd5b505050506040513d6020811015611d2b57600080fd5b50519392505050565b600082600160a060020a03166317e7dd226040805190810160405280601d81526020017f726563656970742d6372656174652e6578697374733a626f6f6c65616e0000008152508460405160200180838051906020019080838360208310611c405780518252601f199092019160209182019101611c21565b6040805160e0810182526000808252602082018190526060928201839052828201839052608082019290925260a0810182905260c081019190915290565b6040805160808101825260008082526020820181905291810182905260608101919091529056007072696e7465722d6d616e6167657200000000000000000000000000000000006f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000a165627a7a723058200cb16af8bc6c8765105273203cb584ec522af15e98c051d2467e9917d3620ce70029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ONNEXTRECEIPTCREATE = "onNextReceiptCreate";

    public static final String FUNC_SUCCESSCREATE = "successCreate";

    public static final String FUNC_FAILCREATE = "failCreate";

    public static final String FUNC_ONNEXTRECEIPTPRINT = "onNextReceiptPrint";

    public static final String FUNC_SUCCESSPRINT = "successPrint";

    public static final String FUNC_FAILPRINT = "failPrint";

    public static final Event RECEIPTCREATE_EVENT = new Event("ReceiptCreate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RECEIPTPRINT_EVENT = new Event("ReceiptPrint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("89354", "0x6a14921fe32a20b3f2a41aa7caa00476d3155c3b");
    }

    protected PrinterOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PrinterOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<PrinterOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(PrinterOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<PrinterOracle> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(PrinterOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<ReceiptCreateEventResponse> getReceiptCreateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTCREATE_EVENT, transactionReceipt);
        ArrayList<ReceiptCreateEventResponse> responses = new ArrayList<ReceiptCreateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptCreateEventResponse typedResponse = new ReceiptCreateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptCreateEventResponse> receiptCreateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptCreateEventResponse>() {
            @Override
            public ReceiptCreateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTCREATE_EVENT, log);
                ReceiptCreateEventResponse typedResponse = new ReceiptCreateEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptCreateEventResponse> receiptCreateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTCREATE_EVENT));
        return receiptCreateEventObservable(filter);
    }

    public List<ReceiptPrintEventResponse> getReceiptPrintEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECEIPTPRINT_EVENT, transactionReceipt);
        ArrayList<ReceiptPrintEventResponse> responses = new ArrayList<ReceiptPrintEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReceiptPrintEventResponse typedResponse = new ReceiptPrintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._receiptId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._data = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._params = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ReceiptPrintEventResponse> receiptPrintEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ReceiptPrintEventResponse>() {
            @Override
            public ReceiptPrintEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RECEIPTPRINT_EVENT, log);
                ReceiptPrintEventResponse typedResponse = new ReceiptPrintEventResponse();
                typedResponse.log = log;
                typedResponse._commandId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._receiptId = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._data = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._params = (String) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ReceiptPrintEventResponse> receiptPrintEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECEIPTPRINT_EVENT));
        return receiptPrintEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> onNextReceiptCreate(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTRECEIPTCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successCreate(BigInteger _commandId, String _receiptId, String _url) {
        final Function function = new Function(
                FUNC_SUCCESSCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId), 
                new org.web3j.abi.datatypes.Utf8String(_receiptId), 
                new org.web3j.abi.datatypes.Utf8String(_url)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failCreate(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILCREATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> onNextReceiptPrint(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_ONNEXTRECEIPTPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> successPrint(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_SUCCESSPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> failPrint(BigInteger _commandId) {
        final Function function = new Function(
                FUNC_FAILPRINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commandId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static PrinterOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PrinterOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static PrinterOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PrinterOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface PrinterOracleEvent extends SmartContractEvent {
    }

    public static class ReceiptCreateEventResponse implements PrinterOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;
    }

    public static class ReceiptPrintEventResponse implements PrinterOracleEvent {
        public Log log;

        public BigInteger _commandId;

        public BigInteger _sessionId;

        public String _receiptId;

        public String _data;

        public String _params;
    }
}
