$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'orginfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'orgId', name: 'orgId', index: 'org_id', width: 50, key: true },
			{ label: '机构负责人电话', name: 'cPhone', index: 'c_phone', width: 80 }, 			
			{ label: '机构负责人名字', name: 'cName', index: 'c_name', width: 80 }, 			
			{ label: '机构名称', name: 'orgName', index: 'org_name', width: 80 }, 			
			{ label: '机构地址', name: 'orgAddr', index: 'org_addr', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }			
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
		orgInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orgInfo = {};
		},
		update: function (event) {
			var orgId = getSelectedRow();
			if(orgId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orgId)
		},
		saveOrUpdate: function (event) {
			var url = vm.orgInfo.orgId == null ? "orginfo/save" : "orginfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orgInfo),
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
			var orgIds = getSelectedRows();
			if(orgIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "orginfo/delete",
                    contentType: "application/json",
				    data: JSON.stringify(orgIds),
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
		getInfo: function(orgId){
			$.get(baseURL + "orginfo/info/"+orgId, function(r){
                vm.orgInfo = r.orgInfo;
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