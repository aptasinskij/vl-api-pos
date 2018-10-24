package com.skysoft.vaultlogic.contracts;

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
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
    private static final String BINARY = "0x60806040523480156200001157600080fd5b50604051602080620023648339810160408181529151828201909252600f8082527f73657373696f6e2d73746f7261676500000000000000000000000000000000006020830190815260008054600160a060020a031916331781558493909290916200008191600191906200044e565b505081600160a060020a031663713b563f6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015620000db57600080fd5b505af1158015620000f0573d6000803e3d6000fd5b505050506040513d60208110156200010757600080fd5b505160038054600160a060020a031916600160a060020a03928316179055604080517fd0496d6a00000000000000000000000000000000000000000000000000000000815290519184169163d0496d6a916004808201926020929091908290030181600087803b1580156200017b57600080fd5b505af115801562000190573d6000803e3d6000fd5b505050506040513d6020811015620001a757600080fd5b505160028054600160a060020a031916600160a060020a03928316178082556040517f693ec85e00000000000000000000000000000000000000000000000000000000815260206004820190815260018054610100818316150260001901169490940460248301819052929094169363693ec85e939290918291604490910190849080156200027a5780601f106200024e576101008083540402835291602001916200027a565b820191906000526020600020905b8154815290600101906020018083116200025c57829003601f168201915b505092505050602060405180830381600087803b1580156200029b57600080fd5b505af1158015620002b0573d6000803e3d6000fd5b505050506040513d6020811015620002c757600080fd5b50519050600160a060020a038116156200034d5780600160a060020a03166341c0e1b56040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b1580156200033357600080fd5b505af115801562000348573d6000803e3d6000fd5b505050505b600280546040517ff2c298be00000000000000000000000000000000000000000000000000000000815260206004820190815260018054600019818316156101000201169490940460248301819052600160a060020a039093169363f2c298be9390928291604490910190849080156200040b5780601f10620003df576101008083540402835291602001916200040b565b820191906000526020600020905b815481529060010190602001808311620003ed57829003601f168201915b505092505050600060405180830381600087803b1580156200042c57600080fd5b505af115801562000441573d6000803e3d6000fd5b50505050505050620004f3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200049157805160ff1916838001178555620004c1565b82800160010185558215620004c1579182015b82811115620004c1578251825591602001919060010190620004a4565b50620004cf929150620004d3565b5090565b620004f091905b80821115620004cf5760008155600101620004da565b90565b611e6180620005036000396000f3006080604052600436106100ab5763ffffffff60e060020a60003504166306fdde0381146100b0578063402ff0db1461013a57806341c0e1b5146101d85780635a1695af146101ef5780635c622a0e1461021b5780637e55698e1461024557806382e408131461025d5780638da5cb5b14610278578063901135b7146102a9578063985d7b7d146103405780639d46ec8714610358578063c9d3f98114610370578063fd198b2c14610414575b600080fd5b3480156100bc57600080fd5b506100c5610431565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100ff5781810151838201526020016100e7565b50505050905090810190601f16801561012c5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561014657600080fd5b506101526004356104be565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561019b578181015183820152602001610183565b50505050905090810190601f1680156101c85780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b3480156101e457600080fd5b506101ed6104ef565b005b3480156101fb57600080fd5b50610207600435610555565b604080519115158252519081900360200190f35b34801561022757600080fd5b5061023360043561057a565b60408051918252519081900360200190f35b34801561025157600080fd5b506100c56004356105a4565b34801561026957600080fd5b506101ed6004356024356105c3565b34801561028457600080fd5b5061028d61062b565b60408051600160a060020a039092168252519081900360200190f35b3480156102b557600080fd5b506102c160043561063a565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156103045781810151838201526020016102ec565b50505050905090810190601f1680156103315780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34801561034c57600080fd5b50610233600435610659565b34801561036457600080fd5b506102c1600435610678565b34801561037c57600080fd5b50604080516020600460443581810135601f81018490048402850184019095528484526101ed94823594602480359536959460649492019190819084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b8a359b909a9099940197509195509182019350915081908401838280828437509497506106859650505050505050565b34801561042057600080fd5b506101ed60043560243515156107c4565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156104b65780601f1061048b576101008083540402835291602001916104b6565b820191906000526020600020905b81548152906001019060200180831161049957829003601f168201915b505050505081565b60035460009060609082906104e290600160a060020a03168563ffffffff6107e516565b9250925092509193909250565b600054600160a060020a03163214610551576040805160e560020a62461bcd02815260206004820152601260248201527f6f6e6c79206f776e657220616c6c6f7765640000000000000000000000000000604482015290519081900360640190fd5b6000ff5b60035460009061057490600160a060020a03168363ffffffff610cd716565b92915050565b60035460009061059990600160a060020a03168363ffffffff610e4516565b600481111561057457fe5b60035460609061057490600160a060020a03168363ffffffff610fac16565b6105ec828260048111156105d357fe5b600354600160a060020a0316919063ffffffff61117a16565b604080518381526020810183905281517f150b944d2f668fb8c44fa57a4dd1c95f72d46a1efea6a92a497a002cf491f47b929181900390910190a15050565b600054600160a060020a031681565b6000606061064783610659565b9150610652836105a4565b9050915091565b60035460009061057490600160a060020a03168363ffffffff6112de16565b600060606106478361057a565b6003546106a590600160a060020a0316868686868663ffffffff61135716565b7f4877137176325aa35905a2381bdc594ccaf7b63c49d97380c22e2269c6ac96498585858585604051808681526020018581526020018060200184815260200180602001838103835286818151815260200191508051906020019080838360005b8381101561071e578181015183820152602001610706565b50505050905090810190601f16801561074b5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b8381101561077e578181015183820152602001610766565b50505050905090810190601f1680156107ab5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390a15050505050565b6003546107e190600160a060020a0316838363ffffffff611a5916565b5050565b6000606060006107f58585611bfe565b151561084b576040805160e560020a62461bcd02815260206004820152601560248201527f53657373696f6e206973206e6f74206578697374730000000000000000000000604482015290519081900360640190fd5b84600160a060020a031663e82617fb6040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f696400000000000000000000815250866040516020018083805190602001908083835b602083106108c35780518252601f1990920191602091820191016108a4565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106109235780518252601f199092019160209182019101610904565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b15801561098457600080fd5b505af1158015610998573d6000803e3d6000fd5b505050506040513d60208110156109ae57600080fd5b5051604080518082018252600f8082527f73657373696f6e5f785f746f6b656e000000000000000000000000000000000060208381019182529351949750600160a060020a038a169463a209a29c948a9391019182918083835b60208310610a275780518252601f199092019160209182019101610a08565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610a875780518252601f199092019160209182019101610a68565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b158015610aec57600080fd5b505af1158015610b00573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526020811015610b2957600080fd5b810190808051640100000000811115610b4157600080fd5b82016020810184811115610b5457600080fd5b8151640100000000811182820187101715610b6e57600080fd5b5050929190505050915084600160a060020a031663e82617fb6040805190810160405280600e8152602001600080516020611e16833981519152815250866040516020018083805190602001908083835b60208310610bde5780518252601f199092019160209182019101610bbf565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610c3e5780518252601f199092019160209182019101610c1f565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610c9f57600080fd5b505af1158015610cb3573d6000803e3d6000fd5b505050506040513d6020811015610cc957600080fd5b505192959194509192509050565b600082600160a060020a03166317e7dd226040805190810160405280601a81526020017f73657373696f6e5f6861735f6163746976655f636173685f696e000000000000815250846040516020018083805190602001908083835b60208310610d515780518252601f199092019160209182019101610d32565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610db15780518252601f199092019160209182019101610d92565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610e1257600080fd5b505af1158015610e26573d6000803e3d6000fd5b505050506040513d6020811015610e3c57600080fd5b50519392505050565b600082600160a060020a031663e82617fb6040805190810160405280600e8152602001600080516020611e16833981519152815250846040516020018083805190602001908083835b60208310610ead5780518252601f199092019160209182019101610e8e565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310610f0d5780518252601f199092019160209182019101610eee565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a0283526004830152925160248083019650939450929083900301905081600087803b158015610f6e57600080fd5b505af1158015610f82573d6000803e3d6000fd5b505050506040513d6020811015610f9857600080fd5b50516004811115610fa557fe5b9392505050565b606082600160a060020a031663a209a29c6040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e0000000000000000000000000000000000815250846040516020018083805190602001908083835b602083106110265780518252601f199092019160209182019101611007565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106110865780518252601f199092019160209182019101611067565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152915160248084019550600094509092839003019050818387803b1580156110eb57600080fd5b505af11580156110ff573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f19168201604052602081101561112857600080fd5b81019080805164010000000081111561114057600080fd5b8201602081018481111561115357600080fd5b815164010000000081118282018710171561116d57600080fd5b5090979650505050505050565b82600160a060020a031663ffb4f6236040805190810160405280600e8152602001600080516020611e16833981519152815250846040516020018083805190602001908083835b602083106111e05780518252601f1990920191602091820191016111c1565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106112405780518252601f199092019160209182019101611221565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902083600481111561127957fe5b6040518363ffffffff1660e060020a02815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b1580156112c157600080fd5b505af11580156112d5573d6000803e3d6000fd5b50505050505050565b600082600160a060020a031663e82617fb6040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f6964000000000000000000008152508460405160200180838051906020019080838360208310610d515780518252601f199092019160209182019101610d32565b6113618686611bfe565b156113b6576040805160e560020a62461bcd02815260206004820152601960248201527f53657373696f6e20697320616c72656164792065786973747300000000000000604482015290519081900360640190fd5b6113cf600160a060020a0387168263ffffffff611c7716565b1515611425576040805160e560020a62461bcd02815260206004820152601360248201527f4b696f736b206973206e6f742065786973747300000000000000000000000000604482015290519081900360640190fd5b61143e600160a060020a0387168563ffffffff611d9c16565b1515611494576040805160e560020a62461bcd02815260206004820152601960248201527f4170706c69636174696f6e206973206e6f742065786973747300000000000000604482015290519081900360640190fd5b85600160a060020a031663ffb4f6236040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f696400000000000000000000815250876040516020018083805190602001908083835b6020831061150c5780518252601f1990920191602091820191016114ed565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b6020831061156c5780518252601f19909201916020918201910161154d565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a0282526004820152602481018b9052915160448084019550600094509092839003019050818387803b1580156115d857600080fd5b505af11580156115ec573d6000803e3d6000fd5b5050505085600160a060020a031663f58660666040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e0000000000000000000000000000000000815250876040516020018083805190602001908083835b602083106116685780518252601f199092019160209182019101611649565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106116c85780518252601f1990920191602091820191016116a9565b51815160209384036101000a60001901801990921691161790526040805192909401829003822063ffffffff881660e060020a02835260048301818152602484019586528c5160448501528c519197508c96509493506064909201919085019080838360005b8381101561174657818101518382015260200161172e565b50505050905090810190601f1680156117735780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b15801561179357600080fd5b505af11580156117a7573d6000803e3d6000fd5b5050505085600160a060020a031663ffb4f6236040805190810160405280600e8152602001600080516020611e16833981519152815250876040516020018083805190602001908083835b602083106118115780518252601f1990920191602091820191016117f2565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106118715780518252601f199092019160209182019101611852565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a028252600482015260248101899052915160448084019550600094509092839003019050818387803b1580156118dd57600080fd5b505af11580156118f1573d6000803e3d6000fd5b5050505085600160a060020a0316633eba9ed26040805190810160405280600e81526020017f73657373696f6e2e657869737473000000000000000000000000000000000000815250876040516020018083805190602001908083835b6020831061196d5780518252601f19909201916020918201910161194e565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b602083106119cd5780518252601f1990920191602091820191016119ae565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a028252600482015260016024820152915160448084019550600094509092839003019050818387803b158015611a3957600080fd5b505af1158015611a4d573d6000803e3d6000fd5b50505050505050505050565b611a638383611bfe565b1515611ab9576040805160e560020a62461bcd02815260206004820152601560248201527f53657373696f6e206973206e6f74206578697374730000000000000000000000604482015290519081900360640190fd5b82600160a060020a0316633eba9ed26040805190810160405280601a81526020017f73657373696f6e5f6861735f6163746976655f636173685f696e000000000000815250846040516020018083805190602001908083835b60208310611b315780518252601f199092019160209182019101611b12565b51815160209384036101000a600019018019909216911617905292019384525060408051808503815293820190819052835193945092839250908401908083835b60208310611b915780518252601f199092019160209182019101611b72565b5181516020939093036101000a60001901801990911692169190911790526040805191909301819003812063ffffffff871660e060020a02825260048201528715156024820152915160448084019550600094509092839003019050818387803b1580156112c157600080fd5b600082600160a060020a03166317e7dd226040805190810160405280600e81526020017f73657373696f6e2e6578697374730000000000000000000000000000000000008152508460405160200180838051906020019080838360208310610d515780518252601f199092019160209182019101610d32565b600082600160a060020a03166317e7dd226040805190810160405280600c81526020017f6b696f736b2e6578697374730000000000000000000000000000000000000000815250846040516020018083805190602001908083835b60208310611cf15780518252601f199092019160209182019101611cd2565b51815160209384036101000a600019018019909216911617905285519190930192850191508083835b60208310611d395780518252601f199092019160209182019101611d1a565b6001836020036101000a0380198251168184511680821785525050505050509050019250505060405160208183030381529060405260405180828051906020019080838360208310610db15780518252601f199092019160209182019101610d92565b600082600160a060020a03166317e7dd226040805190810160405280601281526020017f6170706c69636174696f6e2e65786973747300000000000000000000000000008152508460405160200180838051906020019080838360208310610d515780518252601f199092019160209182019101610d32560073657373696f6e5f737461747573000000000000000000000000000000000000a165627a7a72305820c2e9401a00c55cda74ebca68ac4d2c4221d8dd555e2584089619cefd0c760cef0029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SAVE = "save";

    public static final String FUNC_GETSESSION = "getSession";

    public static final String FUNC_SETHASACTIVECASHIN = "setHasActiveCashIn";

    public static final String FUNC_ISHASACTIVECASHIN = "isHasActiveCashIn";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_GETSTATUSANDXTOKEN = "getStatusAndXToken";

    public static final String FUNC_GETAPPID = "getAppId";

    public static final String FUNC_GETXTOKEN = "getXToken";

    public static final String FUNC_GETAPPIDANDXTOKEN = "getAppIdAndXToken";

    public static final String FUNC_SETSTATUS = "setStatus";

    public static final Event SAVED_EVENT = new Event("Saved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event STATUSUPDATED_EVENT = new Event("StatusUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ACTIVECASHIN_EVENT = new Event("ActiveCashIn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("84107", "0x244ab81ff850f5d4b2f2201bf9409264ba34a395");
        _addresses.put("37609", "0xd704de83d040d6dda4942aa4fde721618d8b406f");
        _addresses.put("5777", "0x2a56b970bc8a2f0f5d83742fb98d7f9716ce0acd");
    }

    protected SessionStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public static RemoteCall<SessionStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
        return deployRemoteCall(SessionStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _config) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_config)));
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
            typedResponse.kioskId = (String) eventValues.getNonIndexedValues().get(4).getValue();
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
                typedResponse.kioskId = (String) eventValues.getNonIndexedValues().get(4).getValue();
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

    public List<ActiveCashInEventResponse> getActiveCashInEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ACTIVECASHIN_EVENT, transactionReceipt);
        ArrayList<ActiveCashInEventResponse> responses = new ArrayList<ActiveCashInEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ActiveCashInEventResponse typedResponse = new ActiveCashInEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._flag = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ActiveCashInEventResponse> activeCashInEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ActiveCashInEventResponse>() {
            @Override
            public ActiveCashInEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ACTIVECASHIN_EVENT, log);
                ActiveCashInEventResponse typedResponse = new ActiveCashInEventResponse();
                typedResponse.log = log;
                typedResponse._sessionId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._flag = (Boolean) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ActiveCashInEventResponse> activeCashInEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ACTIVECASHIN_EVENT));
        return activeCashInEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> save(BigInteger sessionId, BigInteger appId, String xToken, BigInteger status, String kioskId) {
        final Function function = new Function(
                FUNC_SAVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(appId), 
                new org.web3j.abi.datatypes.Utf8String(xToken), 
                new org.web3j.abi.datatypes.generated.Uint256(status), 
                new org.web3j.abi.datatypes.Utf8String(kioskId)), 
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

    public RemoteCall<TransactionReceipt> setHasActiveCashIn(BigInteger _sessionId, Boolean _flag) {
        final Function function = new Function(
                FUNC_SETHASACTIVECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.Bool(_flag)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isHasActiveCashIn(BigInteger _sessionId) {
        final Function function = new Function(FUNC_ISHASACTIVECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public interface SessionStorageEvent extends SmartContractEvent {
    }

    public static class SavedEventResponse implements SessionStorageEvent {
        public Log log;

        public BigInteger sessionId;

        public BigInteger appId;

        public String xToken;

        public BigInteger status;

        public String kioskId;
    }

    public static class StatusUpdatedEventResponse implements SessionStorageEvent {
        public Log log;

        public BigInteger index;

        public BigInteger status;
    }

    public static class ActiveCashInEventResponse implements SessionStorageEvent {
        public Log log;

        public BigInteger _sessionId;

        public Boolean _flag;
    }
}
