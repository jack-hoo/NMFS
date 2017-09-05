$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customeraccount/list',
        datatype: "json",
        colModel: [			
			{ label: 'cAccountId', name: 'cAccountId', index: 'c_account_id', width: 50, key: true },
			{ label: '套餐总流量', name: 'dataFlowTotal', index: 'data_flow_total', width: 80 }, 			
			{ label: '客户id', name: 'customerId', index: 'customer_id', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		customerAccount: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerAccount = {};
		},
		update: function (event) {
			var cAccountId = getSelectedRow();
			if(cAccountId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cAccountId)
		},
		saveOrUpdate: function (event) {
			var url = vm.customerAccount.cAccountId == null ? "customeraccount/save" : "customeraccount/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.customerAccount),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var cAccountIds = getSelectedRows();
			if(cAccountIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "customeraccount/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cAccountIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(cAccountId){
			$.get(baseURL + "customeraccount/info/"+cAccountId, function(r){
                vm.customerAccount = r.customerAccount;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});