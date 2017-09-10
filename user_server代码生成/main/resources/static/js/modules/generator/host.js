$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'host/list',
        datatype: "json",
        colModel: [			
			{ label: 'hostId', name: 'hostId', index: 'host_id', width: 50, key: true },
			{ label: '所属机构id', name: 'orgId', index: 'org_id', width: 80 }, 			
			{ label: '主播账号id', name: 'cId', index: 'c_id', width: 80 }, 			
			{ label: '主播名称', name: 'hostName', index: 'host_name', width: 80 }, 			
			{ label: '主播qq', name: 'hostQq', index: 'host_qq', width: 80 }, 			
			{ label: '主播性别：0男，1女', name: 'hostSex', index: 'host_sex', width: 80 }, 			
			{ label: '主播邮箱', name: 'hostEmail', index: 'host_email', width: 80 }, 			
			{ label: '主播电话', name: 'hostPhone', index: 'host_phone', width: 80 }, 			
			{ label: '主播头像', name: 'hostAvatar', index: 'host_avatar', width: 80 }, 			
			{ label: '部门组织', name: 'department', index: 'department', width: 80 }, 			
			{ label: '备注信息', name: 'remark', index: 'remark', width: 80 }, 			
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
		host: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.host = {};
		},
		update: function (event) {
			var hostId = getSelectedRow();
			if(hostId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(hostId)
		},
		saveOrUpdate: function (event) {
			var url = vm.host.hostId == null ? "host/save" : "host/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.host),
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
			var hostIds = getSelectedRows();
			if(hostIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "host/delete",
                    contentType: "application/json",
				    data: JSON.stringify(hostIds),
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
		getInfo: function(hostId){
			$.get(baseURL + "host/info/"+hostId, function(r){
                vm.host = r.host;
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