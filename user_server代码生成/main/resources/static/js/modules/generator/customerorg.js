$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customerorg/list',
        datatype: "json",
        colModel: [			
			{ label: 'customerOrgId', name: 'customerOrgId', index: 'customer_org_id', width: 50, key: true },
			{ label: '客户id(包含小主播)', name: 'customerId', index: 'customer_id', width: 80 }, 			
			{ label: '机构id', name: 'orgId', index: 'org_id', width: 80 }			
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
		customerOrg: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerOrg = {};
		},
		update: function (event) {
			var customerOrgId = getSelectedRow();
			if(customerOrgId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(customerOrgId)
		},
		saveOrUpdate: function (event) {
			var url = vm.customerOrg.customerOrgId == null ? "customerorg/save" : "customerorg/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.customerOrg),
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
			var customerOrgIds = getSelectedRows();
			if(customerOrgIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "customerorg/delete",
                    contentType: "application/json",
				    data: JSON.stringify(customerOrgIds),
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
		getInfo: function(customerOrgId){
			$.get(baseURL + "customerorg/info/"+customerOrgId, function(r){
                vm.customerOrg = r.customerOrg;
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