/**
 *  构建商品分类树 
 */
$('#tree').tree({
    url:'/ezsh/gdClassifyAd/gtTree',
    lines:true,
});

$('#tree').tree({
	onClick: function(node){
		$("#pGoodsClassfyIdValue").textbox("setValue",node.text);
		$("#pGoodsClassfyId").val(node.id);
	}
});