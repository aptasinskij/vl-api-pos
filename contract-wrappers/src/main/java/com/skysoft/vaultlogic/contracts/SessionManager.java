package com.skysoft.vaultlogic.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
public class SessionManager extends Contract {
    private static final String BINARY = "0x60806040523480156200001157600080fd5b5060405160208062002b9d8339810180604052810190808051906020019092919050505080336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff161415151562000106576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631e59c5296200019e620002ae640100000000026401000000009004565b306040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b838110156200023e57808201518184015260208101905062000221565b50505050905090810190601f1680156200026c5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156200028d57600080fd5b505af1158015620002a2573d6000803e3d6000fd5b505050505050620002eb565b60606040805190810160405280600f81526020017f73657373696f6e2d6d616e616765720000000000000000000000000000000000815250905090565b6128a280620002fb6000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806308190726146100885780635a1695af1461014b57806382afd23b14610190578063882d5f7a146101d55780638da5cb5b14610202578063a3bfdf4714610259578063b260c42a14610286575b600080fd5b34801561009457600080fd5b506101496004803603810190808035906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506102cb565b005b34801561015757600080fd5b506101766004803603810190808035906020019092919050505061040f565b604051808215151515815260200191505060405180910390f35b34801561019c57600080fd5b506101bb60048036038101908080359060200190929190505050610448565b604051808215151515815260200191505060405180910390f35b3480156101e157600080fd5b506102006004803603810190808035906020019092919050505061049a565b005b34801561020e57600080fd5b50610217610674565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561026557600080fd5b5061028460048036038101908080359060200190929190505050610699565b005b34801561029257600080fd5b506102b160048036038101908080359060200190929190505050610846565b604051808215151515815260200191505060405180910390f35b6102d3612829565b600060e0604051908101604052808781526020018681526020018481526020018581526020016000600481111561030657fe5b815260200160001515815260200160001515815250915061034d8261032961096c565b73ffffffffffffffffffffffffffffffffffffffff166109b190919063ffffffff16565b5061037e8661035a61096c565b73ffffffffffffffffffffffffffffffffffffffff1661165490919063ffffffff16565b90508073ffffffffffffffffffffffffffffffffffffffff1663d7bbd1d4876040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156103ef57600080fd5b505af1158015610403573d6000803e3d6000fd5b50505050505050505050565b60006104418261041d61096c565b73ffffffffffffffffffffffffffffffffffffffff1661170e90919063ffffffff16565b9050919050565b60006001600481111561045757fe5b6104878361046361096c565b73ffffffffffffffffffffffffffffffffffffffff166118d590919063ffffffff16565b600481111561049257fe5b149050919050565b6000806104cd836104a961096c565b73ffffffffffffffffffffffffffffffffffffffff166118d590919063ffffffff16565b9150600360048111156104dc57fe5b8260048111156104e857fe5b141515610583576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f53657373696f6e20636f6e6669726d20636c6f7365206661696c65642e20526581526020017f71756972656420434c4f53455f5245515545535445442073746174652e00000081525060400191505060405180910390fd5b6105b683600461059161096c565b73ffffffffffffffffffffffffffffffffffffffff16611aa79092919063ffffffff16565b6105e6836105c261096c565b73ffffffffffffffffffffffffffffffffffffffff1661165490919063ffffffff16565b90508073ffffffffffffffffffffffffffffffffffffffff1663ae1f1ee1846040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561065757600080fd5b505af115801561066b573d6000803e3d6000fd5b50505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006106cb826106a761096c565b73ffffffffffffffffffffffffffffffffffffffff166118d590919063ffffffff16565b9050600160048111156106da57fe5b8160048111156106e657fe5b141515610781576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260348152602001807f53657373696f6e207265717565737420636c6f7365206661696c65642e20526581526020017f717569726564204143544956452073746174652e00000000000000000000000081525060400191505060405180910390fd5b6107b482600361078f61096c565b73ffffffffffffffffffffffffffffffffffffffff16611aa79092919063ffffffff16565b6107bc611c59565b73ffffffffffffffffffffffffffffffffffffffff1663a3bfdf47836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561082a57600080fd5b505af115801561083e573d6000803e3d6000fd5b505050505050565b6000806108798361085561096c565b73ffffffffffffffffffffffffffffffffffffffff166118d590919063ffffffff16565b90506000600481111561088857fe5b81600481111561089457fe5b14151561092f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260328152602001807f53657373696f6e2061637469766174696f6e206661696c65642e20526571756981526020017f726564204352454154494e47207374617465000000000000000000000000000081525060400191505060405180910390fd5b61096283600161093d61096c565b73ffffffffffffffffffffffffffffffffffffffff16611aa79092919063ffffffff16565b6001915050919050565b60006109ac6040805190810160405280600881526020017f6461746162617365000000000000000000000000000000000000000000000000815250611c9e565b905090565b60006109c1838360000151611e67565b151515610a36576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f53657373696f6e20697320616c7265616479206578697374730000000000000081525060200191505060405180910390fd5b610a6382604001518473ffffffffffffffffffffffffffffffffffffffff1661202e90919063ffffffff16565b1515610ad7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260138152602001807f4b696f736b206973206e6f74206578697374730000000000000000000000000081525060200191505060405180910390fd5b610b0482602001518473ffffffffffffffffffffffffffffffffffffffff1661224290919063ffffffff16565b1515610b78576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f4170706c69636174696f6e206973206e6f74206578697374730000000000000081525060200191505060405180910390fd5b8273ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236040805190810160405280600a81526020017f73657373696f6e5f69640000000000000000000000000000000000000000000081525084600001516040516020018083805190602001908083835b602083101515610c075780518252602082019150602081019050602083039250610be2565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515610c775780518252602082019150602081019050602083039250610c52565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902084600001516040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015610d0a57600080fd5b505af1158015610d1e573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f69640000000000000000000081525084600001516040516020018083805190602001908083835b602083101515610db15780518252602082019150602081019050602083039250610d8c565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515610e215780518252602082019150602081019050602083039250610dfc565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902084602001516040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015610eb457600080fd5b505af1158015610ec8573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663f58660666040805190810160405280601081526020017f73657373696f6e5f6b696f736b5f69640000000000000000000000000000000081525084600001516040516020018083805190602001908083835b602083101515610f5b5780518252602082019150602081019050602083039250610f36565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515610fcb5780518252602082019150602081019050602083039250610fa6565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902084604001516040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561107657808201518184015260208101905061105b565b50505050905090810190601f1680156110a35780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156110c357600080fd5b505af11580156110d7573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663f58660666040805190810160405280600f81526020017f73657373696f6e5f785f746f6b656e000000000000000000000000000000000081525084600001516040516020018083805190602001908083835b60208310151561116a5780518252602082019150602081019050602083039250611145565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156111da57805182526020820191506020810190506020830392506111b5565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902084606001516040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561128557808201518184015260208101905061126a565b50505050905090810190601f1680156112b25780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156112d257600080fd5b505af11580156112e6573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236040805190810160405280600e81526020017f73657373696f6e5f73746174757300000000000000000000000000000000000081525084600001516040516020018083805190602001908083835b6020831015156113795780518252602082019150602081019050602083039250611354565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156113e957805182526020820191506020810190506020830392506113c4565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208460800151600481111561142657fe5b6040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b15801561148757600080fd5b505af115801561149b573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff16633eba9ed26040805190810160405280600e81526020017f73657373696f6e2e65786973747300000000000000000000000000000000000081525084600001516040516020018083805190602001908083835b60208310151561152e5780518252602082019150602081019050602083039250611509565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561159e5780518252602082019150602081019050602083039250611579565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060016040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600019166000191681526020018215151515815260200192505050600060405180830381600087803b15801561163257600080fd5b505af1158015611646573d6000803e3d6000fd5b505050506001905092915050565b60006116608383611e67565b15156116d4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260158152602001807f53657373696f6e206973206e6f7420657869737473000000000000000000000081525060200191505060405180910390fd5b6117066116e18484612409565b8473ffffffffffffffffffffffffffffffffffffffff166125d090919063ffffffff16565b905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff166317e7dd226040805190810160405280601a81526020017f73657373696f6e5f6861735f6163746976655f636173685f696e000000000000815250846040516020018083805190602001908083835b60208310151561179b5780518252602082019150602081019050602083039250611776565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561180b57805182526020820191506020810190506020830392506117e6565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561189257600080fd5b505af11580156118a6573d6000803e3d6000fd5b505050506040513d60208110156118bc57600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb6040805190810160405280600e81526020017f73657373696f6e5f737461747573000000000000000000000000000000000000815250846040516020018083805190602001908083835b602083101515611962578051825260208201915060208101905060208303925061193d565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156119d257805182526020820191506020810190506020830392506119ad565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611a5957600080fd5b505af1158015611a6d573d6000803e3d6000fd5b505050506040513d6020811015611a8357600080fd5b81019080805190602001909291905050506004811115611a9f57fe5b905092915050565b8273ffffffffffffffffffffffffffffffffffffffff1663ffb4f6236040805190810160405280600e81526020017f73657373696f6e5f737461747573000000000000000000000000000000000000815250846040516020018083805190602001908083835b602083101515611b325780518252602082019150602081019050602083039250611b0d565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515611ba25780518252602082019150602081019050602083039250611b7d565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020836004811115611bdb57fe5b6040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180836000191660001916815260200182815260200192505050600060405180830381600087803b158015611c3c57600080fd5b505af1158015611c50573d6000803e3d6000fd5b50505050505050565b6000611c996040805190810160405280600e81526020017f73657373696f6e2d6f7261636c65000000000000000000000000000000000000815250611c9e565b905090565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663693ec85e836040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b83811015611d4a578082015181840152602081019050611d2f565b50505050905090810190601f168015611d775780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b158015611d9657600080fd5b505af1158015611daa573d6000803e3d6000fd5b505050506040513d6020811015611dc057600080fd5b8101908080519060200190929190505050905060008173ffffffffffffffffffffffffffffffffffffffff1614151515611e62576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260168152602001807f53797374656d2073746174652076696f6c6174696f6e0000000000000000000081525060200191505060405180910390fd5b919050565b60008273ffffffffffffffffffffffffffffffffffffffff166317e7dd226040805190810160405280600e81526020017f73657373696f6e2e657869737473000000000000000000000000000000000000815250846040516020018083805190602001908083835b602083101515611ef45780518252602082019150602081019050602083039250611ecf565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b602083101515611f645780518252602082019150602081019050602083039250611f3f565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b158015611feb57600080fd5b505af1158015611fff573d6000803e3d6000fd5b505050506040513d602081101561201557600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff166317e7dd226040805190810160405280600c81526020017f6b696f736b2e6578697374730000000000000000000000000000000000000000815250846040516020018083805190602001908083835b6020831015156120bb5780518252602082019150602081019050602083039250612096565b6001836020036101000a03801982511681845116808217855250505050505090500182805190602001908083835b60208310151561210e57805182526020820191506020810190506020830392506120e9565b6001836020036101000a038019825116818451168082178552505050505050905001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156121785780518252602082019150602081019050602083039250612153565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156121ff57600080fd5b505af1158015612213573d6000803e3d6000fd5b505050506040513d602081101561222957600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff166317e7dd226040805190810160405280601281526020017f6170706c69636174696f6e2e6578697374730000000000000000000000000000815250846040516020018083805190602001908083835b6020831015156122cf57805182526020820191506020810190506020830392506122aa565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561233f578051825260208201915060208101905060208303925061231a565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b1580156123c657600080fd5b505af11580156123da573d6000803e3d6000fd5b505050506040513d60208110156123f057600080fd5b8101908080519060200190929190505050905092915050565b60008273ffffffffffffffffffffffffffffffffffffffff1663e82617fb6040805190810160405280601681526020017f73657373696f6e5f6170706c69636174696f6e5f696400000000000000000000815250846040516020018083805190602001908083835b6020831015156124965780518252602082019150602081019050602083039250612471565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b60208310151561250657805182526020820191506020810190506020830392506124e1565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561258d57600080fd5b505af11580156125a1573d6000803e3d6000fd5b505050506040513d60208110156125b757600080fd5b8101908080519060200190929190505050905092915050565b60006125dc8383612242565b1515612650576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f4170706c69636174696f6e206973206e6f74206578697374730000000000000081525060200191505060405180910390fd5b8273ffffffffffffffffffffffffffffffffffffffff16634c77e5ba6126ab6040805190810160405280601381526020017f6170706c69636174696f6e5f616464726573730000000000000000000000000081525085612748565b6040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808260001916600019168152602001915050602060405180830381600087803b15801561270557600080fd5b505af1158015612719573d6000803e3d6000fd5b505050506040513d602081101561272f57600080fd5b8101908080519060200190929190505050905092915050565b600082826040516020018083805190602001908083835b602083101515612784578051825260208201915060208101905060208303925061275f565b6001836020036101000a038019825116818451168082178552505050505050905001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156127f457805182526020820191506020810190506020830392506127cf565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020905092915050565b60e060405190810160405280600081526020016000815260200160608152602001606081526020016000600481111561285e57fe5b815260200160001515815260200160001515815250905600a165627a7a723058207e1b94422b7c91402e2774321d91c2c6625adb85b53d7b2f473838cacbec11260029";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_CREATESESSION = "createSession";

    public static final String FUNC_CLOSESESSION = "closeSession";

    public static final String FUNC_CONFIRMCLOSE = "confirmClose";

    public static final String FUNC_ISACTIVE = "isActive";

    public static final String FUNC_ISHASACTIVECASHIN = "isHasActiveCashIn";

    public static final String FUNC_ACTIVATE = "activate";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("77142", "0x323297182758249878a21e81526c93dc1eb16b1c");
    }

    protected SessionManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SessionManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<SessionManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(SessionManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<SessionManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String regAddr) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(regAddr)));
        return deployRemoteCall(SessionManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> createSession(BigInteger _sessionId, BigInteger _appId, String _xToken, String _kioskId) {
        final Function function = new Function(
                FUNC_CREATESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_appId), 
                new org.web3j.abi.datatypes.Utf8String(_xToken), 
                new org.web3j.abi.datatypes.Utf8String(_kioskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeSession(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_CLOSESESSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> confirmClose(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_CONFIRMCLOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> isActive(BigInteger _sessionId) {
        final Function function = new Function(FUNC_ISACTIVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> isHasActiveCashIn(BigInteger _sessionId) {
        final Function function = new Function(FUNC_ISHASACTIVECASHIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> activate(BigInteger _sessionId) {
        final Function function = new Function(
                FUNC_ACTIVATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_sessionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static SessionManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SessionManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SessionManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public interface SessionManagerEvent extends SmartContractEvent {
    }
}
