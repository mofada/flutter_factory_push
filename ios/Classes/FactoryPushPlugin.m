#import "FactoryPushPlugin.h"
#if __has_include(<factory_push/factory_push-Swift.h>)
#import <factory_push/factory_push-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "factory_push-Swift.h"
#endif

@implementation FactoryPushPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFactoryPushPlugin registerWithRegistrar:registrar];
}
@end
