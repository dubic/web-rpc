/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var RPC = {
    url: "rpc",
    data:{
        className : null,
        method : null,
        parameters :[]
    },
    call: function(cls,meth,params,success,error){
        this.data.className = cls;
        this.data.method = meth;
        this.data.parameters = params;
        $.ajax({
            type:"post",
            url : this.url,
            dataType: "json",
            data: {"rpc": JSON.stringify(this.data)},
            success: function(r){
               success(r);
            },
            error : function(r){
                error(r);
            }
        });
      
    }
}
