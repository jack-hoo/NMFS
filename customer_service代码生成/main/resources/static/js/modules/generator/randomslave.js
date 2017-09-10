$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'randomslave/list',
        datatype: "json",
        colModel: [			
			{ label: 'slaveAccount', name: 'slaveAccount', index: 'slave_account', width: 50, key: true },
			{ label: '申请者的主账号', name: 'masterId', index: 'master_id', width: 80 }			
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
		randomSlave: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.randomSlave = {};
		},
		update: function (event) {
			var slaveAccount = getSelectedRow();
			if(slaveAccount == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(slaveAccount)
		},
		saveOrUpdate: function (event) {
			var url = vm.randomSlave.slaveAccount == null ? "randomslave/save" : "randomslave/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.randomSlave),
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
			var slaveAccounts = getSelectedRows();
			if(slaveAccounts == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "randomslave/delete",
                    contentType: "application/json",
				    data: JSON.stringify(slaveAccounts),
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
		getInfo: function(slaveAccount){
			$.get(baseURL + "randomslave/info/"+slaveAccount, function(r){
                vm.randomSlave = r.randomSlave;
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