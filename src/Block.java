
public class Block {

    //数据
    //之前区块的hash
    //自己的hash
    public String data;
    public String previoushash;
    public String hash;
    //下一个block
//    public Block  next;
    //随机数
    public long nonce = 1;

    public void setData(String data) {
        this.data = data;
    }

    public String getData(){
        return this.data;
    }

    public Block(String data, String previoushash){
        this.data = data;
        this.previoushash = previoushash;
        this.hash = calculateHash();
    }

    //根据当前区块的data和前一个区块的hash计算当前区块的hash
    public String calculateHash(){
        return  Sha256.getSHA256(this.data + this.previoushash+this.nonce);
    }
}
